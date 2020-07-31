package com.project.university.services;

import com.project.university.models.Subject;

import java.util.List;

public interface SubjectService {

    Subject getSubject(int id);

    List<Subject> getAllSubjects();

    Subject saveSubject(Subject subject);

    void updateSubject(Subject subject);

    void deleteSubject(int id);
}
