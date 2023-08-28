package com.database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="SUBSIDIARY")
@Data
public class SubsidiaryEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NAME")
    private String name;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ADDRESS", referencedColumnName = "ID")
    private AddressEntity address;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "MANAGER", referencedColumnName = "ID")
    private EmployeeEntity manager;
}
