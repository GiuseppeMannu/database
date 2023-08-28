package com.database.model;

import lombok.Data;

@Data
public class AddressModel {

    private int id;
    
    private String country;
    
    private String division;
    
    private String city;
    
    private String street;
    
    private String number;

    private String postalCode;
}

