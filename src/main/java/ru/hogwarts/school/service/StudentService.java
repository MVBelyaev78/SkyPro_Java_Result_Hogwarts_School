package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student addStudent(Student student);

    Student findStudent(Long id);

    Student editStudent(Student student);

    void deleteStudent(Long id);

    Collection<Student> findByAge(Integer age);

    Collection<Student> findByAgeBetween(Integer startAge, Integer endAge);

    Avatar findAvatar(long studentId);

    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;
}
