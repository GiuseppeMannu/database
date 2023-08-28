package com.database.model;

import com.database.deserializer.EmployeeDeserializer;
import com.database.deserializer.PerformanceDeserializer;
import com.database.entity.EmployeeEntity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;

@Data
@JsonDeserialize(using = PerformanceDeserializer.class)
public class PerformanceModel {

    private int id;

    private EmployeeModel employeeId;

    private Date date;

    private int rating;
}
