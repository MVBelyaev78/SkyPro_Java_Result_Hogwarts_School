package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private static Long count = 1L;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(count++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (!faculties.containsKey(faculty.getId())) {
            return null;
        }
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public void deleteFaculty(Long id) {
        faculties.remove(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return faculties
                .values()
                .stream()
                .filter(faculty -> Objects.equals(faculty.getColor(), color))
                .collect(Collectors.toList());
    }
}
