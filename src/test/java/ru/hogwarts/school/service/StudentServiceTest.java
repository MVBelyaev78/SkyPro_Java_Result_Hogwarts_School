package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepositoryMock;

    @InjectMocks
    private StudentServiceImpl out;

    @Test
    public void should_addStudent_succeed() {
        final Student studentSource = new Student("Aurelius Dumbledore", 14);
        final Student studentTarget = new Student(1L, "Aurelius Dumbledore", 14);
        when(studentRepositoryMock.save(eq(studentSource))).thenReturn(studentTarget);
        assertEquals(studentTarget, out.addStudent(studentSource));
        verify(studentRepositoryMock, times(1)).save(studentSource);
    }

    @Test
    public void should_findStudent_succeed() {
        final Student student = new Student(1L, "Bellatrix Lestrange", 22);
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(student));
        assertEquals(student, out.findStudent(1L));
        verify(studentRepositoryMock, times(1)).findById(1L);
    }

    @Test
    public void should_findStudent_not_found() {
        final Student studentEmpty = new Student();
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(studentEmpty));
        assertEquals(studentEmpty, out.findStudent(1L));
        verify(studentRepositoryMock, times(1)).findById(1L);
    }

    @Test
    public void should_editStudent_succeed() {
        final Student student = new Student(1L, "Gilderoy Lockhart", 16);
        when(studentRepositoryMock.save(eq(student))).thenReturn(student);
        assertEquals(student, out.addStudent(student));
        verify(studentRepositoryMock, times(1)).save(student);
    }

    @Test
    public void should_deleteStudent_succeed() {
        out.deleteStudent(1L);
        verify(studentRepositoryMock, times(1)).deleteById(1L);
    }
}
