package com.project.university.repositories;

import com.project.university.models.Chair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChairRepository extends JpaRepository<Chair, Integer> {
}
