package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final FacultyService facultyService;

    public StudentController(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        if (students == null || students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping(value = "{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student,
                                 @RequestParam(name = "faculty_id") Long faculty_id) {
        student.setFaculty(facultyService.findFaculty(faculty_id));
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student,
                                               @RequestParam(name = "faculty_id") Long faculty_id) {
        student.setFaculty(facultyService.findFaculty(faculty_id));
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping(value = "{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "age", params = "age")
    public ResponseEntity<Collection<Student>> findByAge(@RequestParam(name = "age") Integer age) {
        Collection<Student> students = studentService.findByAge(age);
        if (students == null || students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping(value = "age_between", params = {"age1", "age2"})
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam(name = "age1") Integer age1,
                                                                @RequestParam(name = "age2") Integer age2) {
        Collection<Student> students = studentService.findByAgeBetween(age1, age2);
        if (students == null || students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping(value = "{studentId}/faculty")
    public ResponseEntity<Faculty> getStudentsFaculty(@PathVariable Long studentId) {
        Faculty faculty = studentService.findStudent(studentId).getFaculty();
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> findStudentsCount() {
        return ResponseEntity.ok(studentService.findStudentsCount());
    }

    @GetMapping(value = "/average_age")
    public ResponseEntity<Double> findStudentsAverageAge() {
        return ResponseEntity.ok(studentService.findStudentsAverageAge());
    }

    @GetMapping(value = "/last", params = "num")
    public ResponseEntity<Collection<Student>> findLastStudents(@RequestParam(name = "num") Integer num) {
        if (num <= 0) {
            return ResponseEntity.notFound().build();
        }

        Collection<Student> students = studentService.findLastStudents(num);
        if (students == null || students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping(value = "/first_symbol", params = "firstSymbol")
    public ResponseEntity<List<String>> findStudentsWithNamesFromSymbol(@RequestParam(name = "firstSymbol") char firstSymbol) {
        List<String> studentsNames = studentService.findStudentsWithNamesFromSymbol(firstSymbol);
        if (studentsNames == null || studentsNames.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentsNames);
    }
}
