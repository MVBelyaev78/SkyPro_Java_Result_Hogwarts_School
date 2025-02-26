package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {

    List<Avatar> findAvatarInDataBase(Integer pageNumber, Integer pageSize);

    Avatar findAvatarInDataBaseByStudent(long studentId);

    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;
}
