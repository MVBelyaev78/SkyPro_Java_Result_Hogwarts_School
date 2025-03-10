package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        logger.info("StudentService.findAll");
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        logger.info("StudentService.addStudent: name={}", student.getName());
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("StudentService.findStudent: id={}", id);
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student editStudent(Student student) {
        logger.info("StudentService.editStudent: id={}", student.getId());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("StudentService.deleteStudent: id={}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(Integer age) {
        logger.info("StudentService.findByAge: age={}", age);
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(Integer startAge, Integer endAge) {
        logger.info("StudentService.findByAgeBetween: startAge={}, endAge={}", startAge, endAge);
        return studentRepository.findByAgeBetween(startAge, endAge);
    }

    public Long findStudentsCount() {
        logger.info("StudentService.findStudentsCount");
        return studentRepository.findStudentsCount();
    }

    public Double findStudentsAverageAge() {
        logger.info("StudentService.findStudentsAverageAge");
        return studentRepository.findStudentsAverageAge();
    }

    public Collection<Student> findLastStudents(Integer num) {
        logger.info("StudentService.findLastStudents: num={}", num);
        return studentRepository.findLastStudents(num);
    }

    public List<String> findStudentsWithNamesFromSymbol(String firstWord) {
        return studentRepository
                .findAll()
                .stream()
                .filter(s -> s.getName().startsWith(firstWord))
                .map(s -> s.getName().toUpperCase())
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    public OptionalDouble findStudentsAverageAge2() {
        return studentRepository
                .findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .average();
    }
}
