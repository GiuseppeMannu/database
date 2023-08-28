package com.database.model;
import com.database.deserializer.EmployeeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import java.util.Date;

@Data
@JsonDeserialize(using = EmployeeDeserializer.class)
public class EmployeeModel {

    private int id;

    private String firstName;

    private String secondName;

    private Date hireDate;

    private JobModel jobId;
}

