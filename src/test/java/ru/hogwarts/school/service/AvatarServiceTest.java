package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AvatarServiceTest {
    @Mock
    private AvatarRepository avatarRepositoryMock;

    @InjectMocks
    AvatarServiceImpl out;

    @Test
    public void should_findAvatarInDataBase_succeed() {
        final Avatar avatar1 = new Avatar();
        avatar1.setId(1L);
        avatar1.setFilePath("file_path/avatar1");
        avatar1.setFileSize(1);
        avatar1.setMediaType("text/plain");
        avatar1.setStudent(new Student(1L,"John Lennon", 20));
        avatar1.setData(new byte[]{0, 1});

        final Avatar avatar2 = new Avatar();
        avatar2.setId(2L);
        avatar2.setFilePath("file_path/avatar2");
        avatar2.setFileSize(1);
        avatar2.setMediaType("text/plain");
        avatar2.setStudent(new Student(2L,"George Harrison", 17));
        avatar2.setData(new byte[]{0, 1});

        PageRequest pageRequest = PageRequest.of(0, 2);
        when(avatarRepositoryMock.findAll(pageRequest)).thenReturn(new PageImpl<>(List.of(avatar1, avatar2)));
        assertEquals(List.of(avatar1, avatar2), out.findAvatarInDataBase(1, 2));
    }

    @Test
    public void should_findAvatarInDataBase_not_found() {
        assertThrows(IllegalArgumentException.class, () -> out.findAvatarInDataBase(0, 2));
    }

    @Test
    public void should_findAvatarInDataBaseByStudent_succeed() {
        final Avatar avatar = new Avatar();
        avatar.setId(1L);
        avatar.setFilePath("file_path");
        avatar.setFileSize(1);
        avatar.setMediaType("text/plain");
        avatar.setStudent(new Student(1L,"John Lennon", 20));
        avatar.setData(new byte[]{0, 1});

        when(avatarRepositoryMock.findByStudentId(1L)).thenReturn(Optional.of(avatar));
        assertEquals(avatar, out.findAvatarInDataBaseByStudent(1L));
    }

    @Test
    public void should_findAvatarInDataBaseByStudent_not_found() {
        when(avatarRepositoryMock.findByStudentId(1L)).thenReturn(Optional.empty());
        assertThrows(AvatarNotFoundException.class, () -> out.findAvatarInDataBaseByStudent(1L));
    }
}
