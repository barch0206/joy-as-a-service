package com.joy.repository;

import com.joy.model.PendingJoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingJoyRepository extends JpaRepository<PendingJoy, Long> {
    // Basic save/findAll are included by default
}