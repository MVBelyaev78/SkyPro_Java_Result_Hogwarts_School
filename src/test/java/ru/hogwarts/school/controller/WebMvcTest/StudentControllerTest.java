package ru.hogwarts.school.controller.WebMvcTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    public void test_getAllStudents_succeed() throws Exception {
        List<Student> arrResult = new ArrayList<>();
        arrResult.add(new Student("John Lennon", 30));
        arrResult.add(new Student("George Harrison", 28));
        when(studentService.findAll()).thenReturn(arrResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/student"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].name").value("John Lennon"))
                .andExpect(jsonPath("$[0].age").value(30))
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[1].name").value("George Harrison"))
                .andExpect(jsonPath("$[1].age").value(28))
                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    public void test_getAllStudents_when_students_do_not_exist() throws Exception {
        when(studentService.findAll()).thenThrow(StudentNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/student"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_getStudentById_succeed() throws Exception {
        when(studentService.findStudent(anyLong())).thenReturn(new Student("John Lennon", 30));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("John Lennon"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    public void test_getStudentById_when_student_does_not_exist() throws Exception {
        when(studentService.findStudent(anyLong())).thenThrow(StudentNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_createStudent_succeed() throws Exception {
        final Student student = new Student("John Lennon", 30);
        when(studentService.addStudent(any(Student.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("John Lennon"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    public void test_editStudent_succeed() throws Exception {
        final Student student = new Student("John Lennon", 30);
        when(studentService.editStudent(student)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("John Lennon"))
                .andExpect(jsonPath("$.age").value(30));
    }
}
