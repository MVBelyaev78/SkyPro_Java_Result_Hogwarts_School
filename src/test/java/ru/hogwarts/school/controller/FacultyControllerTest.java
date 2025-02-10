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
    public void testGetFacultyById() throws Exception {
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
    public void testGetFacultyByIdWhenFacultyDoesNotExists() throws Exception {
        when(facultyService.findFaculty(anyLong()))
                .thenThrow(FacultyNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateFaculty() throws Exception {
        final Faculty faculty = new Faculty("Griffindor 110", "dark-green");
        when(facultyService.addFaculty(any(Faculty.class)))
                .thenReturn(faculty);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Griffindor 110"))
                .andExpect(jsonPath("$.color").value("dark-green"));
    }
}
