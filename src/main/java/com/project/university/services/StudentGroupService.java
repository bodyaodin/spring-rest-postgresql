package com.project.university.services;

import com.project.university.models.StudentGroup;

import java.util.List;

public interface StudentGroupService {

    StudentGroup getStudentGroup(int id);

    List<StudentGroup> getAllStudentGroups();

    StudentGroup saveStudentGroup(StudentGroup studentGroup);

    void updateStudentGroup(StudentGroup studentGroup);

    void deleteStudentGroup(int id);
}
