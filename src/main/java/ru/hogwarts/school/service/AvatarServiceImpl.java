package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    final AvatarRepository avatarRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar findAvatarInDataBase(long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }
}
