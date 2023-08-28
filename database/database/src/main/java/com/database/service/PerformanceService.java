package com.database.service;


import com.database.entity.PerformanceEntity;
import com.database.mapping.PerformanceMappingImplementation;
import com.database.model.PerformanceModel;
import com.database.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceService {
    @Autowired
    private PerformanceRepository performanceRepository;
    private final PerformanceMappingImplementation mapping = new PerformanceMappingImplementation();

    public void insert (PerformanceModel performanceModel){
        performanceModel.setId(this.performanceRepository.findFirstByOrderByIdDesc().getId()+1);
        PerformanceEntity performanceEntity = mapping.toEntity(performanceModel);
        try {
            this.performanceRepository.save(performanceEntity);
        }catch(DataIntegrityViolationException d){

            this.insert(performanceModel);
        }
    }

    public List<PerformanceModel> getAll(){
        List<PerformanceModel> performanceesModel = new ArrayList<>();

        for(PerformanceEntity j : this.performanceRepository.findAll()){
            PerformanceModel performanceModel = mapping.toModel(j);
            performanceesModel.add(performanceModel);
        }

        return performanceesModel;
    }
}
