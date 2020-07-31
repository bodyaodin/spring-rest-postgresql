package com.project.university.services;

import com.project.university.models.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty getFaculty(int id);

    List<Faculty> getAllFaculties();

    Faculty saveFaculty(Faculty faculty);

    void updateFaculty(Faculty faculty);

    void deleteFaculty(int id);
}
