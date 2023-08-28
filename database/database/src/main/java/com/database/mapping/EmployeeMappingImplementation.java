package com.database.mapping;

import com.database.entity.EmployeeEntity;
import com.database.model.EmployeeModel;

public class EmployeeMappingImplementation implements EmployeeMapping {

    private JobMappingImplementation jobMapping = new JobMappingImplementation();

    public EmployeeModel toModel(EmployeeEntity employeeEntity){
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(employeeEntity.getId());
        employeeModel.setFirstName(employeeEntity.getFirstName());
        employeeModel.setSecondName(employeeEntity.getSecondName());
        employeeModel.setHireDate(employeeEntity.getHireDate());
        try {
            employeeModel.setJobId(this.jobMapping.toModel(employeeEntity.getJobId()));
        }catch(NullPointerException n){
            employeeModel.setJobId(null);
        }
        return employeeModel;
    }

    public EmployeeEntity toEntity(EmployeeModel employeeModel){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeModel.getId());
        employeeEntity.setFirstName(employeeModel.getFirstName());
        employeeEntity.setSecondName(employeeModel.getSecondName());
        employeeEntity.setHireDate(employeeModel.getHireDate());

        try {
            employeeEntity.setJobId(this.jobMapping.toEntity(employeeModel.getJobId()));
        }catch(NullPointerException n){
            employeeEntity.setJobId(null);
        }
        return employeeEntity;
    }
}
