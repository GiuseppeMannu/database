package com.database.model;

import com.database.deserializer.SubsidiaryDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize(using = SubsidiaryDeserializer.class)
public class SubsidiaryModel {

    private int id;

    private String name;

    private AddressModel address;

    private EmployeeModel manager;
}

