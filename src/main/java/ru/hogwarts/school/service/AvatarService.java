package ru.hogwarts.school.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Avatar;

public interface AvatarService {

    Avatar findAvatarInDataBase(long studentId);
}
