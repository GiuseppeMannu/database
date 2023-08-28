package com.database.service;

import com.database.entity.EmployeeEntity;
import com.database.entity.JobEntity;
import com.database.mapping.JobMappingImplementation;
import com.database.model.JobModel;
import com.database.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    private final JobMappingImplementation mapping = new JobMappingImplementation();


    public void insert (JobModel jobModel){

        jobModel.setId(this.jobRepository.findFirstByOrderByIdDesc().getId()+1);

        JobEntity jobEntity = mapping.toEntity(jobModel);
        try {
            this.jobRepository.save(jobEntity);
        }catch(DataIntegrityViolationException d){
            this.insert(jobModel);
        }
    }

    public List<JobModel> getAll(){
        List<JobModel> jobsModel = new ArrayList<>();

        for(JobEntity j : this.jobRepository.findAll()){
            JobModel jobModel = mapping.toModel(j);
            jobsModel.add(jobModel);
        }

        return jobsModel;
    }
}
