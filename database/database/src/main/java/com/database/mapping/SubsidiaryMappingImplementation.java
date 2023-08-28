package com.database.mapping;

import com.database.entity.SubsidiaryEntity;
import com.database.model.SubsidiaryModel;

public class SubsidiaryMappingImplementation implements SubsidiaryMapping {

    AddressMappingImplementation addressMapping = new AddressMappingImplementation();
    EmployeeMappingImplementation employeeMapping = new EmployeeMappingImplementation();

    public SubsidiaryModel toModel(SubsidiaryEntity subsidiaryEntity){
        SubsidiaryModel subsidiaryModel = new SubsidiaryModel();
        subsidiaryModel.setId(subsidiaryEntity.getId());
        subsidiaryModel.setName(subsidiaryEntity.getName());
        subsidiaryModel.setAddress(addressMapping.toModel(subsidiaryEntity.getAddress()));
        subsidiaryModel.setManager(employeeMapping.toModel(subsidiaryEntity.getManager()));
        return subsidiaryModel;
    }

    public SubsidiaryEntity toEntity(SubsidiaryModel subsidiaryModel){
        SubsidiaryEntity subsidiaryEntity = new SubsidiaryEntity();
        subsidiaryEntity.setId(subsidiaryModel.getId());
        subsidiaryEntity.setName(subsidiaryModel.getName());
        subsidiaryEntity.setAddress(addressMapping.toEntity(subsidiaryModel.getAddress()));
        subsidiaryEntity.setManager(employeeMapping.toEntity(subsidiaryModel.getManager()));
        return subsidiaryEntity;
    }
}
