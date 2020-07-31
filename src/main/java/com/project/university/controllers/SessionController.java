package com.project.university.controllers;

import com.project.university.models.Session;
import com.project.university.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Session getSession(@PathVariable int id) {
        return sessionService.getSession(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Session addSession(@RequestBody Session session) {
        return sessionService.saveSession(session);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSession(@RequestBody Session session) {
        sessionService.updateSession(session);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable int id) {
        sessionService.deleteSession(id);
    }
}
