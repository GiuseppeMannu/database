package com.database.mapping;

import com.database.entity.JobEntity;
import com.database.model.JobModel;

public interface JobMapping {
    JobModel toModel(JobEntity jobEntity);

    JobEntity toEntity(JobModel jobModel);
}
