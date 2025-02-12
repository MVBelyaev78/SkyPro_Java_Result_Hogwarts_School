package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacultyService facultyService;

    @Test
    public void test_getAllFaculties_succeed() throws Exception {
        List<Faculty> arrResult = new ArrayList<>();
        arrResult.add(new Faculty("Hufflepuff", "black"));
        arrResult.add(new Faculty("Slytherin", "silver"));
        when(facultyService.findAll()).thenReturn(arrResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].name").value("Hufflepuff"))
                .andExpect(jsonPath("$[0].color").value("black"))
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[1].name").value("Slytherin"))
                .andExpect(jsonPath("$[1].color").value("silver"))
                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    public void test_getAllFaculties_when_faculties_do_not_exist() throws Exception {
        when(facultyService.findAll())
                .thenThrow(FacultyNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_GetFacultyById_succeed() throws Exception {
        when(facultyService.findFaculty(anyLong()))
                .thenReturn(new Faculty("Griffindor", "brown"));
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Griffindor"))
                .andExpect(jsonPath("$.color").value("brown"));
    }

    @Test
    public void test_GetFacultyById_when_faculty_does_not_exist() throws Exception {
        when(facultyService.findFaculty(anyLong())).thenThrow(FacultyNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_CreateFaculty_succeed() throws Exception {
        final Faculty faculty = new Faculty("Ravenclaw", "dark-blue");
        when(facultyService.addFaculty(any(Faculty.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(faculty)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Ravenclaw"))
                .andExpect(jsonPath("$.color").value("dark-blue"));
    }

    @Test
    public void test_editFaculty_succeed() throws Exception {
        final Faculty faculty = new Faculty("Gryffindor", "dark-red");
        when(facultyService.editFaculty(faculty)).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(faculty)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("dark-red"));
    }
}
