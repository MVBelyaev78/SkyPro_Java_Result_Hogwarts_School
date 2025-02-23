package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        if (students == null || students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "age")
    public ResponseEntity<Collection<Student>> findByAge(@RequestParam(name = "age") Integer age) {
        Collection<Student> students = studentService.findByAge(age);
        if (students == null || students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping(params = {"age1", "age2"})
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam(name = "age1") Integer age1,
                                                                @RequestParam(name = "age2") Integer age2) {
        Collection<Student> students = studentService.findByAgeBetween(age1, age2);
        if (students == null || students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("{studentId}/faculty")
    public ResponseEntity<Faculty> getStudentsFaculty(@PathVariable Long studentId) {
        Faculty faculty = studentService.findStudent(studentId).getFaculty();
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

//    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> uploadAvatar(@PathVariable Long id,
//                                               @RequestParam MultipartFile avatar) throws IOException {
//        if (avatar.getSize() > 1024 * 300) {
//            return ResponseEntity.badRequest().body("File is too big");
//        }
//
//        studentService.uploadAvatar(id, avatar);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping(value = "/{id}/avatar/preview")
//    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
//        Avatar avatar = studentService.findAvatar(id);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
//        headers.setContentLength(avatar.getData().length);
//
//        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
//    }
//
//    @GetMapping(value = "/{id}/avatar")
//    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
//        Avatar avatar = studentService.findAvatar(id);
//
//        Path path = Path.of(avatar.getFilePath());
//
//        try (InputStream is = Files.newInputStream(path);
//             OutputStream os = response.getOutputStream();) {
//            response.setStatus(HttpStatus.OK.value());
//            response.setContentType(avatar.getMediaType());
//            response.setContentLength((int) avatar.getFileSize());
//            is.transferTo(os);
//        }
//    }
}
