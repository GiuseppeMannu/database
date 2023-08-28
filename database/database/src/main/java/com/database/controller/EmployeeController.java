package com.database.controller;

import com.database.model.EmployeeModel;
import com.database.service.EmployeeService;
import com.database.utilities.EmployeeToJobAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@EnableScheduling
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService = new EmployeeService();

    @GetMapping("/employees")
    public List<EmployeeModel> getAll(){
        return employeeService.getAll();
    }
    @PostMapping(
            path = "/employees/assignJob",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void assignJob(
            @RequestBody EmployeeToJobAssociation association
    ){
        employeeService.assignJob(association);    }

    @GetMapping("/employees/findBySalary")
    public List<EmployeeModel> findByName(
            @RequestParam(value = "min", defaultValue = "0") String min,
            @RequestParam(value = "max", defaultValue = "9999999999") String max
    ){
        return employeeService.findBySalary(Integer.parseInt(min), Integer.parseInt(max));
    }

    @PostMapping(
            path = "/employees/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void insert(
            @RequestBody EmployeeModel employeeModel
            ){

        employeeService.insert(employeeModel);
    }

    @PostMapping(
            path = "/employees/remove",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void remove(
            @RequestBody EmployeeModel employeeModel
    ){

        employeeService.remove(employeeModel);
    }

    //cron = ss mm hh dd MM w
    //@Scheduled(cron = " 0 0 9 01 09 *", zone = "Europe/Rome")
    @GetMapping("/test")
    public LinkedHashMap<EmployeeModel, Float> givePromotion(){
        return employeeService.givePromotion(2, (float)6.5);
    }

    @GetMapping("/employees/averagePerformance")
    public Map<EmployeeModel, Float> getAveragePerformance(
            @RequestParam(value = "from", defaultValue = "1970-01-01") String from,
            @RequestParam(value = "to", defaultValue = "3000-31-12") String to
    ){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date fromDate = new Date();
        Date toDate = new Date();

        try {
            fromDate = formatter.parse(from);
            toDate = formatter.parse(to);
        }
        catch(ParseException p){
            fromDate = new Date(0);
            toDate = new Date(31557600000000L);
        }

        return employeeService.getAveragePerformance(fromDate, toDate);
    }

}
