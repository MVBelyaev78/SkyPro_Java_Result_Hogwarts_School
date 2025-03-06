package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(Integer startAge, Integer endAge) {
        return studentRepository.findByAgeBetween(startAge, endAge);
    }

    public Long findStudentsCount() {
        return studentRepository.findStudentsCount();
    }

    public Double findStudentsAverageAge() {
        return studentRepository.findStudentsAverageAge();
    }

    public Collection<Student> findLastStudents(Integer num) {
        return studentRepository.findLastStudents(num);
    }

    public List<String> findStudentsWithNamesFromSymbol(char firstSymbol) {
        return studentRepository
                .findAll()
                .stream()
                .filter(s -> s.getName().toUpperCase().charAt(0) == firstSymbol)
                .map(s -> s.getName().toUpperCase())
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }
}
