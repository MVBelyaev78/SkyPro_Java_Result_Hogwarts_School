package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> findAll() {
        logger.info("FacultyService.findAll");
        return facultyRepository.findAll();
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("FacultyService.addFaculty: name={}", faculty.getName());
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        logger.info("FacultyService.findFaculty: id={}", id);
        return facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("FacultyService.editFaculty: id={}", faculty.getId());
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.info("FacultyService.deleteFaculty: id={}", id);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("FacultyService.findByColor: color={}", color);
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByNameOrColorContainsIgnoreCase(String stringFilter) {
        logger.info("FacultyService.findByNameOrColorContainsIgnoreCase: stringFilter={}", stringFilter);
        return facultyRepository.findByNameOrColorContainsIgnoreCase(stringFilter);
    }
}
