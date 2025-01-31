package ru.hogwarts.school.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static ru.hogwarts.school.constant.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepositoryMock;

    @InjectMocks
    private FacultyServiceImpl out;

    @Test
    @Order(1)
    public void should_addFaculty_succeed() {
        when(facultyRepositoryMock.save(eq(FACULTY_SOURCE_1))).thenReturn(FACULTY_TARGET_1);
        assertEquals(FACULTY_TARGET_1, out.addFaculty(FACULTY_SOURCE_1));
        verify(facultyRepositoryMock, times(1)).save(FACULTY_SOURCE_1);
    }

//    @Test
//    @Order(1)
//    public void should_findFaculty_succeed() {
//        when(facultyRepositoryMock.findById(FACULTY_ID_1)).thenReturn(Optional.of(FACULTY_TARGET_1));
//        assertEquals(FACULTY_TARGET_1, out.findFaculty(FACULTY_TARGET_1.getId()));
//        verify(facultyRepositoryMock, times(1)).findById(FACULTY_ID_1);
//    }

    @Test
    @Order(1)
    public void should_findFaculty_not_found() {
        when(facultyRepositoryMock.findById(FACULTY_ID_1)).thenReturn(Optional.of(new Faculty()));
        assertEquals(new Faculty(), out.findFaculty(FACULTY_ID_1));
    }

    @Test
    @Order(1)
    public void should_editFaculty_succeed() {
        when(facultyRepositoryMock.save(eq(FACULTY_TARGET_1))).thenReturn(FACULTY_TARGET_1);
        assertEquals(FACULTY_TARGET_1, out.addFaculty(FACULTY_TARGET_1));
        verify(facultyRepositoryMock, times(1)).save(FACULTY_TARGET_1);
    }

//    @Test
//    @Order(2)
//    public void should_deleteFaculty_succeed() {
//        out.deleteFaculty(out.addFaculty(FACULTY_1).getId());
//        assertNull(out.findFaculty(FACULTY_1.getId()));
//    }
//
//    @Test
//    @Order(1)
//    public void should_deleteFaculty_not_found() {
//        out.deleteFaculty(FACULTY_1.getId());
//        assertNull(out.findFaculty(FACULTY_1.getId()));
//    }
//
//    @Test
//    @Order(2)
//    public void should_findByColor_succeed() {
//        out.addFaculty(FACULTY_1);
//        out.addFaculty(FACULTY_11);
//        out.addFaculty(FACULTY_2);
//        assertEquals(List.of(FACULTY_11, FACULTY_2), out.findByColor(COLOR_1));
//    }
//
//    @Test
//    @Order(2)
//    public void should_findByColor_not_found() {
//        out.addFaculty(FACULTY_1);
//        assertEquals(Collections.emptyList(), out.findByColor(COLOR_1));
//    }
}
