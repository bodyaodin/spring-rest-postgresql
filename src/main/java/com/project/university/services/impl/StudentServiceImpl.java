package com.project.university.services.impl;

import com.project.university.annotations.ExecutionLog;
import com.project.university.models.Student;
import com.project.university.exceptions.EntityNotFoundException;
import com.project.university.repositories.StudentRepository;
import com.project.university.services.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ExecutionLog
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudent(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id: " + id + " - not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        Student student = studentRepository.getOne(id);
        student.getStudentGroup().removeStudent(student);

        studentRepository.deleteById(id);
    }
}
