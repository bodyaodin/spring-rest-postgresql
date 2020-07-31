package com.project.university.services.impl;

import com.project.university.annotations.ExecutionLog;
import com.project.university.models.Session;
import com.project.university.exceptions.EntityNotFoundException;
import com.project.university.repositories.SessionRepository;
import com.project.university.services.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ExecutionLog
@Transactional
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Session getSession(int id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id: " + id + " - not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public void updateSession(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public void deleteSession(int id) {
        Session session = sessionRepository.getOne(id);
        session.getStudent().removeSession(session);

        sessionRepository.deleteById(id);
    }
}
