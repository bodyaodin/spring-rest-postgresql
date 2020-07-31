package com.project.university.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class MainController {

    @GetMapping
    public String start() {
        return "<h1 style='text-align:center'>Welcome to University DB!</h1>" +
                "<h3>/faculties - Get all faculties from University DB</h3>" +
                "<h3>/chairs - Get all chairs from University DB</h3>" +
                "<h3>/student-groups - Get all student groups from University DB</h3>" +
                "<h3>/students - Get all students from University DB</h3>" +
                "<h3>/subjects - Get all subjects from University DB</h3>" +
                "<h3>/sessions - Get all sessions from University DB</h3>";
    }

}
