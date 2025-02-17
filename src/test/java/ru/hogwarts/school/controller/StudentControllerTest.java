package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void test_getAllStudents_succeed() {
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port, String.class))
                .isNotNull();
    }

    @Test
    void test_getStudentById_succeed() {
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port + "/1", String.class))
                .isNotNull();
    }

    @Test
    void test_createStudent_succeed() {
        Assertions
                .assertThat(restTemplate.postForObject("http://localhost:" + port,
                        new Student("John Lennon", 30),
                        String.class))
                .isNotNull();
    }

    @Test
    void test_editStudent_succeed() {
        final ResponseEntity<Student> response = restTemplate.exchange("http://localhost:" + port,
                HttpMethod.PUT,
                new HttpEntity<>(new Student("John Lennon", 30)),
                Student.class);
        Assertions.assertThat(response.getStatusCode()).isNotNull();
    }

    @Test
    void test_deleteStudent_succeed() {
        final ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/1",
                HttpMethod.DELETE,
                new HttpEntity<>(null),
                Void.class);
        Assertions.assertThat(response.getStatusCode()).isNotNull();
    }
}
