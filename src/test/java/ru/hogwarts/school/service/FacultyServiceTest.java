package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepositoryMock;

    @InjectMocks
    private FacultyServiceImpl out;

    @Test
    public void should_findAll_succeed() {
        final Faculty faculty1 = new Faculty(1L,"Gryffindor", "red");
        final Faculty faculty2 = new Faculty(2L,"Hufflepuff", "yellow");
        when(facultyRepositoryMock.findAll()).thenReturn(List.of(faculty1, faculty2));
        assertEquals(List.of(faculty1, faculty2), out.findAll());
        verify(facultyRepositoryMock, times(1)).findAll();
    }

    @Test
    public void should_findAll_not_found() {
        when(facultyRepositoryMock.findAll()).thenReturn(List.of());
        assertEquals(List.of(), out.findAll());
        verify(facultyRepositoryMock, times(1)).findAll();
    }

    @Test
    public void should_addFaculty_succeed() {
        final Faculty facultySource = new Faculty("Gryffindor", "red");
        final Faculty facultyTarget = new Faculty(1L,"Gryffindor", "red");
        when(facultyRepositoryMock.save(eq(facultySource))).thenReturn(facultyTarget);
        assertEquals(facultyTarget, out.addFaculty(facultySource));
        verify(facultyRepositoryMock, times(1)).save(facultySource);
    }

    @Test
    public void should_findFaculty_succeed() {
        final Faculty faculty = new Faculty(1L,"Hufflepuff", "yellow");
        when(facultyRepositoryMock.findById(1L)).thenReturn(Optional.of(faculty));
        assertEquals(faculty, out.findFaculty(1L));
        verify(facultyRepositoryMock, times(1)).findById(1L);
    }

    @Test
    public void should_findFaculty_not_found() {
        final Faculty facultyEmpty = new Faculty();
        when(facultyRepositoryMock.findById(1L)).thenReturn(Optional.of(facultyEmpty));
        assertEquals(facultyEmpty, out.findFaculty(1L));
        verify(facultyRepositoryMock, times(1)).findById(1L);
    }

    @Test
    public void should_editFaculty_succeed() {
        final Faculty faculty = new Faculty(1L,"Ravenclaw", "blue");
        when(facultyRepositoryMock.save(eq(faculty))).thenReturn(faculty);
        assertEquals(faculty, out.addFaculty(faculty));
        verify(facultyRepositoryMock, times(1)).save(faculty);
    }

    @Test
    public void should_deleteFaculty_succeed() {
        out.deleteFaculty(1L);
        verify(facultyRepositoryMock, times(1)).deleteById(1L);
    }

    @Test
    public void should_findByColor_succeed() {
        final Faculty faculty1 = new Faculty(1L,"Hufflepuff", "yellow");
        final Faculty faculty2 = new Faculty(2L,"Ravenclaw", "yellow");
        when(facultyRepositoryMock.findByColor("yellow")).thenReturn(List.of(faculty1, faculty2));
        assertEquals(List.of(faculty1, faculty2), out.findByColor("yellow"));
        verify(facultyRepositoryMock, times(1)).findByColor("yellow");
    }

    @Test
    public void should_findByColor_not_found() {
        when(facultyRepositoryMock.findByColor("red")).thenReturn(List.of());
        assertEquals(List.of(), out.findByColor("red"));
        verify(facultyRepositoryMock, times(1)).findByColor("red");
    }

    @Test
    public void should_findByNameOrColorContainsIgnoreCase_name_succeed() {
        final Faculty faculty1 = new Faculty(1L,"GryfFindor", "scarlet");
        final Faculty faculty2 = new Faculty(2L,"Hufflepuff", "black");
        when(facultyRepositoryMock.findByNameOrColorContainsIgnoreCase("ff"))
                .thenReturn(List.of(faculty1, faculty2));
        assertEquals(List.of(faculty1, faculty2), out.findByNameOrColorContainsIgnoreCase("ff"));
        verify(facultyRepositoryMock, times(1))
                .findByNameOrColorContainsIgnoreCase("ff");
    }

    @Test
    public void should_findByNameOrColorContainsIgnoreCase_color_succeed() {
        final Faculty faculty1 = new Faculty(1L,"Hufflepuff", "light-yellow");
        final Faculty faculty2 = new Faculty(2L,"Ravenclaw", "orange-yelLow");
        when(facultyRepositoryMock.findByNameOrColorContainsIgnoreCase("ello"))
                .thenReturn(List.of(faculty1, faculty2));
        assertEquals(List.of(faculty1, faculty2), out.findByNameOrColorContainsIgnoreCase("ello"));
        verify(facultyRepositoryMock, times(1))
                .findByNameOrColorContainsIgnoreCase("ello");
    }

    @Test
    public void should_findByNameOrColorContainsIgnoreCase_not_found() {
        when(facultyRepositoryMock.findByNameOrColorContainsIgnoreCase("red")).thenReturn(List.of());
        assertEquals(List.of(), out.findByNameOrColorContainsIgnoreCase("red"));
        verify(facultyRepositoryMock, times(1))
                .findByNameOrColorContainsIgnoreCase("red");
    }
}
