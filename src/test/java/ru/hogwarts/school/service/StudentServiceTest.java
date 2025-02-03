package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
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
    public void should_findAll_succeed() {
        final Student student1 = new Student(1L,"Millicent Bulstrode", 15);
        final Student student2 = new Student(2L,"Slytherin Duelling Club Captain", 14);
        when(studentRepositoryMock.findAll()).thenReturn(List.of(student1, student2));
        assertEquals(List.of(student1, student2), out.findAll());
        verify(studentRepositoryMock, times(1)).findAll();
    }

    @Test
    public void should_findAll_not_found() {
        when(studentRepositoryMock.findAll()).thenReturn(List.of());
        assertEquals(List.of(), out.findAll());
        verify(studentRepositoryMock, times(1)).findAll();
    }

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

    @Test
    public void should_findByAge_succeed() {
        final Student student1 = new Student(1L, "Remus John Lupin", 11);
        final Student student2 = new Student(2L, "Ginevra Molly Weasley", 11);
        when(studentRepositoryMock.findByAge(11)).thenReturn(List.of(student1, student2));
        assertEquals(List.of(student1, student2), out.findByAge(11));
        verify(studentRepositoryMock, times(1)).findByAge(11);
    }

    @Test
    public void should_findByAge_not_found() {
        when(studentRepositoryMock.findByAge(13)).thenReturn(List.of());
        assertEquals(List.of(), out.findByAge(13));
        verify(studentRepositoryMock, times(1)).findByAge(13);
    }

    @Test
    public void should_findByAgeBetween_succeed() {
        final Student student1 = new Student(1L, "Stewart Ackerley", 13);
        final Student student2 = new Student(2L, "Roger Davies", 11);
        when(studentRepositoryMock.findByAgeBetween(10, 14)).thenReturn(List.of(student1, student2));
        assertEquals(List.of(student1, student2), out.findByAgeBetween(10, 14));
        verify(studentRepositoryMock, times(1)).findByAgeBetween(10, 14);
    }

    @Test
    public void should_findByAgeBetween_not_found() {
        when(studentRepositoryMock.findByAgeBetween(12, 17)).thenReturn(List.of());
        assertEquals(List.of(), out.findByAgeBetween(12, 17));
        verify(studentRepositoryMock, times(1)).findByAgeBetween(12, 17);
    }
}
