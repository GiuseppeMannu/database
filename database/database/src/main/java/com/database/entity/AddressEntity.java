package com.database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="ADDRESS")
@Data
public class AddressEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    
    @Column(name = "COUNTRY")
    private String country;
    
    @Column(name = "ADMINISTRATIVE_DIVISION")
    private String division;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "STREET")
    private String street;
    
    @Column(name = "NUMBER")
    private String number;
    
    @Column(name = "POSTAL_CODE")
    private String postalCode;
}
