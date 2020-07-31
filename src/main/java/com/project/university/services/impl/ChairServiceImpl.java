package com.project.university.services.impl;

import com.project.university.annotations.ExecutionLog;
import com.project.university.models.Chair;
import com.project.university.exceptions.EntityNotFoundException;
import com.project.university.repositories.ChairRepository;
import com.project.university.services.ChairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ExecutionLog
@Transactional
public class ChairServiceImpl implements ChairService {

    private final ChairRepository chairRepository;

    public ChairServiceImpl(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Chair getChair(int id) {
        return chairRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id: " + id + " - not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chair> getAllChairs() {
        return chairRepository.findAll();
    }

    @Override
    public Chair saveChair(Chair chair) {
        return chairRepository.save(chair);
    }

    @Override
    public void updateChair(Chair chair) {
        chairRepository.save(chair);
    }

    @Override
    public void deleteChair(int id) {
        Chair chair = chairRepository.getOne(id);
        chair.getFaculty().removeChair(chair);

        chairRepository.deleteById(id);
    }
}
