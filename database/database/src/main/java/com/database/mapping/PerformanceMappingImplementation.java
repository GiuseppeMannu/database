package com.database.mapping;

import com.database.entity.PerformanceEntity;
import com.database.model.PerformanceModel;

public class PerformanceMappingImplementation implements PerformanceMapping{

    private EmployeeMappingImplementation employeeMapping = new EmployeeMappingImplementation();

    public PerformanceModel toModel(PerformanceEntity performanceEntity){
        PerformanceModel performanceModel = new PerformanceModel();
        performanceModel.setId(performanceEntity.getId());
        performanceModel.setEmployeeId(employeeMapping.toModel(performanceEntity.getEmployeeId()));
        performanceModel.setDate(performanceEntity.getDate());
        performanceModel.setRating(performanceEntity.getRating());
        return performanceModel;
    }

    public PerformanceEntity toEntity(PerformanceModel performanceModel){
        PerformanceEntity performanceEntity = new PerformanceEntity();
        performanceEntity.setId(performanceModel.getId());
        performanceEntity.setEmployeeId(employeeMapping.toEntity(performanceModel.getEmployeeId()));
        performanceEntity.setDate(performanceModel.getDate());
        performanceEntity.setRating(performanceModel.getRating());
        return performanceEntity;
    }
}
