package com.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="EMPLOYEE")
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    
    @Column(name = "FIRST_NAME")
    private String firstName;
    
    @Column(name = "SECOND_NAME")
    private String secondName;
    
    @Column(name = "HIRE_DATE")
    private Date hireDate;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="JOB_ID", referencedColumnName = "ID")
    private JobEntity jobId;
}
