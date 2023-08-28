package com.database.repository;
import com.database.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
    public EmployeeEntity findFirstByOrderByIdDesc();
}
