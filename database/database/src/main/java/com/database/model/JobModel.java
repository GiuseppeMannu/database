package com.database.model;

import com.database.deserializer.JobDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = JobDeserializer.class)
public class JobModel {

    private int id;

    private String name;

    private float salary;

    private JobModel supervisorId;
}