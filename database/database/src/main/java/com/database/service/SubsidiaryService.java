package com.database.service;

import com.database.entity.SubsidiaryEntity;
import com.database.mapping.SubsidiaryMappingImplementation;
import com.database.model.SubsidiaryModel;
import com.database.repository.SubsidiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubsidiaryService {
    @Autowired
    private SubsidiaryRepository subsidiaryRepository;

    SubsidiaryMappingImplementation mapping = new SubsidiaryMappingImplementation();

    public void insert (SubsidiaryModel subsidiaryModel){

        subsidiaryModel.setId(this.subsidiaryRepository.findFirstByOrderByIdDesc().getId()+1);

        SubsidiaryEntity subsidiaryEntity = mapping.toEntity(subsidiaryModel);
        try{
        this.subsidiaryRepository.save(subsidiaryEntity);
        }catch(DataIntegrityViolationException d){
            this.insert(subsidiaryModel);
        }
    }

    public List<SubsidiaryModel> getAll(){
        List<SubsidiaryModel> restaurantsModel = new ArrayList<>();

        for(SubsidiaryEntity j : this.subsidiaryRepository.findAll()){
            SubsidiaryModel subsidiaryModel = mapping.toModel(j);
            restaurantsModel.add(subsidiaryModel);
        }

        return restaurantsModel;
    }
}
