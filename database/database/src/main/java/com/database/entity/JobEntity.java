package com.database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="JOB")
@Data
public class JobEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "SALARY")
    private float salary;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "SUPERVISOR_ID", referencedColumnName = "ID")
    private JobEntity supervisorId;
}
