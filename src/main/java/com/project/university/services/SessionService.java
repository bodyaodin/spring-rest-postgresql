package com.project.university.services;

import com.project.university.models.Session;

import java.util.List;

public interface SessionService {

    Session getSession(int id);

    List<Session> getAllSessions();

    Session saveSession(Session session);

    void updateSession(Session session);

    void deleteSession(int id);
}
