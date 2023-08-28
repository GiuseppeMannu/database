package com.database.repository;
import com.database.entity.AddressEntity;
import com.database.entity.SubsidiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubsidiaryRepository extends JpaRepository<SubsidiaryEntity, Long> {
    public SubsidiaryEntity findFirstByOrderByIdDesc();
}
