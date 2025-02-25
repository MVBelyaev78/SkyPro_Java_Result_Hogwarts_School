package ru.hogwarts.school.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;

public interface AvatarService {

    Avatar findAvatarInDataBase(long studentId);

    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;
}
