package com.joy.repository;

import com.joy.model.Joy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JoyRepository extends JpaRepository<Joy, Long> {
    @Query(value = "SELECT * FROM joys WHERE is_deleted = false ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Joy findRandomJoy();
}