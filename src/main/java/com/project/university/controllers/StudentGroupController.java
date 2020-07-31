package com.project.university.controllers;

import com.project.university.models.StudentGroup;
import com.project.university.services.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student-groups")
public class StudentGroupController {

    @Autowired
    private StudentGroupService studentGroupService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentGroup> getAllStudentGroups() {
        return studentGroupService.getAllStudentGroups();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentGroup getStudentGroup(@PathVariable int id) {
        return studentGroupService.getStudentGroup(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public StudentGroup addStudentGroup(@RequestBody StudentGroup studentGroup) {
        return studentGroupService.saveStudentGroup(studentGroup);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudentGroup(@RequestBody StudentGroup studentGroup) {
        studentGroupService.updateStudentGroup(studentGroup);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentGroup(@PathVariable int id) {
        studentGroupService.deleteStudentGroup(id);
    }
}
