package ru.hogwarts.school.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hogwarts.school.model.Student;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.hogwarts.school.constant.TestConstants.*;
import static ru.hogwarts.school.constant.TestConstants.STUDENT_2;

@SpringBootTest
public class StudentServiceTest {
    private final StudentService out = new StudentServiceImpl();

    @Test
    @Order(1)
    public void should_addStudent_succeed() {
        assertEquals(STUDENT_1, out.addStudent(STUDENT_1));
    }

    @Test
    @Order(2)
    public void should_addStudent_id_correct() {
        final Student student1 = out.addStudent(STUDENT_1);
        final Student student2 = out.addStudent(STUDENT_2);
        assertTrue(student1.getId() > 0);
        assertTrue(student2.getId() > student1.getId());
    }

    @Test
    @Order(2)
    public void should_findStudent_succeed() {
        out.addStudent(STUDENT_1);
        assertEquals(STUDENT_1, out.findStudent(STUDENT_1.getId()));
    }

    @Test
    @Order(1)
    public void should_findStudent_not_found() {
        assertNull(out.findStudent(STUDENT_1.getId()));
    }

    @Test
    @Order(3)
    public void should_editStudent_succeed() {
        out.addStudent(STUDENT_1);
        final Student studentActual = out.editStudent(new Student(STUDENT_1.getId(),
                STUDENT_1.getName(),
                STUDENT_2.getAge()));
        assertEquals(out.findStudent(STUDENT_1.getId()), studentActual);
    }

    @Test
    @Order(1)
    public void should_editStudent_not_found() {
        assertNull(out.editStudent(STUDENT_1));
    }

    @Test
    @Order(2)
    public void should_deleteStudent_succeed() {
        out.deleteStudent(out.addStudent(STUDENT_1).getId());
        assertNull(out.findStudent(STUDENT_1.getId()));
    }

    @Test
    @Order(1)
    public void should_deleteStudent_not_found() {
        out.deleteStudent(STUDENT_1.getId());
        assertNull(out.findStudent(STUDENT_1.getId()));
    }

    @Test
    @Order(2)
    public void should_findByAge_succeed() {
        out.addStudent(STUDENT_1);
        out.addStudent(STUDENT_2);
        out.addStudent(STUDENT_3);
        assertEquals(List.of(STUDENT_1, STUDENT_3), out.findByAge(AGE_1));
    }

    @Test
    @Order(2)
    public void should_findByAge_not_found() {
        out.addStudent(STUDENT_2);
        assertEquals(Collections.emptyList(), out.findByAge(AGE_1));
    }
}
