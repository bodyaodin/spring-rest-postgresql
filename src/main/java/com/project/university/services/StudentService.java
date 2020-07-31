package com.project.university.services;

import com.project.university.models.Student;

import java.util.List;

public interface StudentService {

    Student getStudent(int id);

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int id);
}
