package com.project.university.services.impl;

import com.project.university.annotations.ExecutionLog;
import com.project.university.models.StudentGroup;
import com.project.university.exceptions.EntityNotFoundException;
import com.project.university.repositories.StudentGroupRepository;
import com.project.university.services.StudentGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ExecutionLog
@Transactional
public class StudentGroupServiceImpl implements StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;

    public StudentGroupServiceImpl(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentGroup getStudentGroup(int id) {
        return studentGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id: " + id + " - not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentGroup> getAllStudentGroups() {
        return studentGroupRepository.findAll();
    }

    @Override
    public StudentGroup saveStudentGroup(StudentGroup studentGroup) {
        return studentGroupRepository.save(studentGroup);
    }

    @Override
    public void updateStudentGroup(StudentGroup studentGroup) {
        studentGroupRepository.save(studentGroup);
    }

    @Override
    public void deleteStudentGroup(int id) {
        StudentGroup studentGroup = studentGroupRepository.getOne(id);
        studentGroup.getChair().removeStudentGroup(studentGroup);

        studentGroupRepository.deleteById(id);
    }
}
