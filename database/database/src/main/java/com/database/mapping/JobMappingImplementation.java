package com.database.mapping;

import com.database.entity.JobEntity;
import com.database.model.JobModel;

public class JobMappingImplementation implements JobMapping{

    public JobModel toModel(JobEntity jobEntity){
        JobModel jobModel = new JobModel();
        jobModel.setId(jobEntity.getId());
        jobModel.setName(jobEntity.getName());
        jobModel.setSalary(jobEntity.getSalary());
        try {
            jobModel.setSupervisorId(this.toModel(jobEntity.getSupervisorId()));
        }catch(NullPointerException n){
            jobModel.setSupervisorId(null);
        }
        return jobModel;
    }

    public JobEntity toEntity(JobModel jobModel){
        JobEntity jobEntity = new JobEntity();
        jobEntity.setId(jobModel.getId());
        jobEntity.setName(jobModel.getName());
        jobEntity.setSalary(jobModel.getSalary());
        try {
            jobEntity.setSupervisorId(this.toEntity(jobModel.getSupervisorId()));
        }catch(NullPointerException n){
            jobEntity.setSupervisorId(null);
        }

        return jobEntity;
    }
}
