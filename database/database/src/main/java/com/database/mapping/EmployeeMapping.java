package com.database.mapping;

import com.database.entity.EmployeeEntity;
import com.database.model.EmployeeModel;

public interface EmployeeMapping {
    EmployeeModel toModel(EmployeeEntity employeeEntity);

    EmployeeEntity toEntity(EmployeeModel employeeModel);
}
