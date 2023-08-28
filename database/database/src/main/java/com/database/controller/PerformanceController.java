package com.database.controller;

import com.database.model.PerformanceModel;
import com.database.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PerformanceController {
    @Autowired
    private final PerformanceService performanceService = new PerformanceService();

    @GetMapping("/performances")
    public List<PerformanceModel> getAll(){
        return performanceService.getAll();
    }

    @PostMapping(
            path = "/performances/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void insert(
            @RequestBody PerformanceModel performanceModel
    ){
        performanceService.insert(performanceModel);
    }
}
