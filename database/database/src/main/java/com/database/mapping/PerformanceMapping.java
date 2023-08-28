package com.database.mapping;

import com.database.entity.PerformanceEntity;
import com.database.model.PerformanceModel;

public interface PerformanceMapping {
    PerformanceModel toModel(PerformanceEntity performanceEntity);

    PerformanceEntity toEntity(PerformanceModel performanceModel);
}
