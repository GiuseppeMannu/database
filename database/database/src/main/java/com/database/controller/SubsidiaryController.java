package com.database.controller;

import com.database.model.SubsidiaryModel;
import com.database.service.SubsidiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubsidiaryController {
    @Autowired
    private final SubsidiaryService subsidiaryService = new SubsidiaryService();

    @GetMapping("/subsidiaries")
    public List<SubsidiaryModel> getAll(){
        return subsidiaryService.getAll();
    }

    @PostMapping(
            path = "/subsidiaries/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void insert(
            @RequestBody SubsidiaryModel subsidiaryModel
    ){
        subsidiaryService.insert(subsidiaryModel);
    }

}
