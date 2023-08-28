package com.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="PAYCHECK")
@Data
public class PaycheckEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    private EmployeeEntity employeeId;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "BASE_SALARY")
    private float baseSalary;

    @Column(name = "PERFORMANCE_BONUS")
    private float performanceBonus;
    
    @Column(name = "CAR_ALLOWANCE")
    private float carAllowance;
    
    @Column(name = "DEDUCTIONS")
    private float deductions;
    
    @Column(name = "BEFORE_TAX_PAY")
    private float beforeTaxPay;

    @Column(name = "TAX_PERCENTAGE")
    private float taxPercentage;

    @Column(name = "AFTER_TAX_PAY")
    private float afterTaxPay;
}
