package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    private Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    final AvatarRepository avatarRepository;
    final StudentService studentService;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    public List<Avatar> findAvatarInDataBase(Integer pageNumber, Integer pageSize) {
        logger.info("findAvatarInDataBase: pageNumber={}, pageSize={}", pageNumber, pageSize);
        return avatarRepository
                .findAll(PageRequest.of(pageNumber - 1, pageSize))
                .getContent();
    }

    public Avatar findAvatarInDataBaseByStudent(long studentId) {
        logger.info("findAvatarInDataBaseByStudent: studentId={}", studentId);
        return avatarRepository.findByStudentId(studentId).orElseThrow(AvatarNotFoundException::new);
    }

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        logger.info("uploadAvatar: studentId={}", studentId);

        Student student = studentService.findStudent(studentId);

        Path filePath = Path.of(avatarsDir,
                format("%s.%s",
                        studentId,
                        getExtension(Objects.requireNonNull(file.getOriginalFilename()))));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElseGet(Avatar::new);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        avatarRepository.save(avatar);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
