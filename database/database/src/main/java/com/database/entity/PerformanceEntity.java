package com.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="PERFORMANCE")
@Data
public class PerformanceEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    private EmployeeEntity employeeId;

    @Column(name = "EVALUATION_DATE")
    private Date date;

    @Column(name = "RATING")
    private int rating;
}
