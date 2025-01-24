package ru.hogwarts.school.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hogwarts.school.model.Faculty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.hogwarts.school.constant.TestConstants.*;

@SpringBootTest
public class FacultyServiceTest {
    private final FacultyService out = new FacultyService();

    @Test
    @Order(1)
    public void should_addFaculty_succeed() {
        assertEquals(FACULTY_1, out.addFaculty(FACULTY_1));
    }

    @Test
    @Order(2)
    public void should_findFaculty_succeed() {
        out.addFaculty(FACULTY_1);
        assertEquals(FACULTY_1, out.findFaculty(FACULTY_1.getId()));
    }

    @Test
    @Order(1)
    public void should_findFaculty_not_found() {
        assertNull(out.findFaculty(FACULTY_1.getId()));
    }

    @Test
    @Order(3)
    public void should_editFaculty_succeed() {
        out.addFaculty(FACULTY_1);
        final Faculty facultyEdited = out.editFaculty(new Faculty(FACULTY_1.getId(),
                FACULTY_2.getName(),
                FACULTY_2.getColor()));
        assertEquals(out.findFaculty(FACULTY_1.getId()), facultyEdited);
    }

    @Test
    @Order(1)
    public void should_editFaculty_not_found() {
        assertNull(out.editFaculty(FACULTY_1));
    }

    @Test
    @Order(2)
    public void should_deleteFaculty_succeed() {
        out.deleteFaculty(out.addFaculty(FACULTY_1).getId());
        assertNull(out.findFaculty(FACULTY_1.getId()));
    }

    @Test
    @Order(1)
    public void should_deleteFaculty_not_found() {
        out.deleteFaculty(FACULTY_1.getId());
        assertNull(out.findFaculty(FACULTY_1.getId()));
    }
}
