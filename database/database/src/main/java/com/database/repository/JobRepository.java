package com.database.repository;
import com.database.entity.AddressEntity;
import com.database.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
    public JobEntity findFirstByOrderByIdDesc();
}
