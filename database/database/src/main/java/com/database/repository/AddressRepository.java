package com.database.repository;
import com.database.entity.AddressEntity;
import com.database.entity.EmployeeEntity;
import com.database.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    public AddressEntity findFirstByOrderByIdDesc();
}
