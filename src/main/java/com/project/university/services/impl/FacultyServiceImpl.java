package com.project.university.services.impl;

import com.project.university.annotations.ExecutionLog;
import com.project.university.exceptions.EntityNotFoundException;
import com.project.university.models.Faculty;
import com.project.university.repositories.FacultyRepository;
import com.project.university.services.FacultyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ExecutionLog
@Transactional
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Faculty getFaculty(int id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id: " + id + " - not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void updateFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(int id) {
        facultyRepository.deleteById(id);
    }
}
