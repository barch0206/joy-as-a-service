package com.joy.repository;

import com.joy.model.Joy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface JoyRepository extends JpaRepository<Joy, Long> {
    long countByIsDeletedFalse();

    Page<Joy> findAllByIsDeletedFalse(Pageable pageable);
}