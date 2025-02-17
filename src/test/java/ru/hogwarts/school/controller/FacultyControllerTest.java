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
import ru.hogwarts.school.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void test_getAllFaculties_succeed() {
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port, String.class))
                .isNotNull();
    }

    @Test
    void test_getFacultyById_succeed() {
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port + "/1", String.class))
                .isNotNull();
    }

    @Test
    void test_createFaculty_succeed() {
        Assertions
                .assertThat(restTemplate.postForObject("http://localhost:" + port,
                        new Faculty("Gryffindor", "red"),
                        String.class))
                .isNotNull();
    }

    @Test
    void test_editFaculty_succeed() {
        final ResponseEntity<Faculty> response = restTemplate.exchange("http://localhost:" + port,
                HttpMethod.PUT,
                new HttpEntity<>(new Faculty("Gryffindor", "red")),
                Faculty.class);
        Assertions.assertThat(response.getStatusCode()).isNotNull();
    }

    @Test
    void test_deleteFaculty_succeed() {
        final ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/1",
                HttpMethod.DELETE,
                new HttpEntity<>(null),
                Void.class);
        Assertions.assertThat(response.getStatusCode()).isNotNull();
    }
}
