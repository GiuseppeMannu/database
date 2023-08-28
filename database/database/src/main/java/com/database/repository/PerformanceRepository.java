package com.database.repository;
import com.database.entity.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<PerformanceEntity, Long> {
    public PerformanceEntity findFirstByOrderByIdDesc();

    public List<PerformanceEntity> findByIdIsNotNullOrderByEmployeeId();
}
