package com.joy.repository;

import com.joy.model.PendingJoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface PendingJoyRepository extends JpaRepository<PendingJoy, Long> {
    long countByIsDeletedFalse();

    Page<PendingJoy> findAllByIsDeletedFalse(Pageable pageable);
}