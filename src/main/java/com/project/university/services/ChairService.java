package com.project.university.services;

import com.project.university.models.Chair;

import java.util.List;

public interface ChairService {

    Chair getChair(int id);

    List<Chair> getAllChairs();

    Chair saveChair(Chair chair);

    void updateChair(Chair chair);

    void deleteChair(int id);
}
