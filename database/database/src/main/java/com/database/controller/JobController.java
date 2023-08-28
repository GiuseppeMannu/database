package com.database.controller;

import com.database.model.JobModel;
import com.database.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {
    @Autowired
    private final JobService jobService = new JobService();

    @GetMapping("/jobs")
    public List<JobModel> getAll(){
        return jobService.getAll();
    }

    @PostMapping(
            path = "/jobs/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void insert(
            @RequestBody JobModel jobModel
    ){
        jobService.insert(jobModel);
    }

}
