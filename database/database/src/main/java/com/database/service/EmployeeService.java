package com.database.service;

import com.database.entity.EmployeeEntity;
import com.database.entity.PerformanceEntity;
import com.database.mapping.EmployeeMappingImplementation;
import com.database.mapping.JobMappingImplementation;
import com.database.model.EmployeeModel;
import com.database.model.JobModel;
import com.database.repository.JobRepository;
import com.database.repository.EmployeeRepository;
import com.database.repository.PerformanceRepository;
import com.database.utilities.EmployeeToJobAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PerformanceRepository performanceRepository;
    private final EmployeeMappingImplementation employeeMapping = new EmployeeMappingImplementation();
    private final JobMappingImplementation jobMapping = new JobMappingImplementation();

    public void insert (EmployeeModel employeeModel){

        employeeModel.setId(this.employeeRepository.findFirstByOrderByIdDesc().getId()+1);

        EmployeeEntity employeeEntity = employeeMapping.toEntity(employeeModel);

        try {
            this.employeeRepository.save(employeeEntity);
        }catch(DataIntegrityViolationException d){
            this.insert(employeeModel);
        }
    }

    public void remove (EmployeeModel employeeModel){
        this.employeeRepository.delete(employeeMapping.toEntity(employeeModel));
    }

    public List<EmployeeModel> getAll(){
        List<EmployeeModel> employeesModel = new ArrayList<>();

        for(EmployeeEntity e : this.employeeRepository.findAll()){
            EmployeeModel employeeModel = employeeMapping.toModel(e);
            employeesModel.add(employeeModel);
        }

        return employeesModel;
    }
    public void assignJob(EmployeeToJobAssociation association){
        EmployeeEntity employeeEntity = employeeRepository.getReferenceById(association.getEmployeeId());
        EmployeeModel employeeModel = employeeMapping.toModel(employeeEntity);
        employeeModel.setJobId(this.jobMapping.toModel(this.jobRepository.getReferenceById(association.getJobId())));
        employeeRepository.delete(employeeEntity);

        this.insert(employeeModel);
    }
    public List<EmployeeModel> findBySalary(Integer min, Integer max){
        List<EmployeeModel> people = new ArrayList<>();

        for(EmployeeEntity p : employeeRepository.findAll()){
            try {
                if (
                        (p.getJobId().getSalary() >= min) &&
                                (p.getJobId().getSalary() <= max)
                ) {
                    people.add(employeeMapping.toModel(p));
                }
            }catch(NullPointerException n){
                if(min==0){
                    people.add(employeeMapping.toModel(p));
                }
            }
        }
        return people;
    }

    public LinkedHashMap<EmployeeModel, Float> givePromotion(int top, float minPerformance){
        LinkedHashMap<EmployeeModel, Float> map = new LinkedHashMap<>();

        for (Map.Entry<EmployeeModel, Float> e : this.getAveragePerformance( new Date(new Date().getTime()-31557600000L) , new Date()).entrySet()) {
            if ((e.getValue() >= minPerformance)&&(map.size()<top)){
                EmployeeModel employeeModel = e.getKey();
                if(
                        (employeeModel.getJobId().getSupervisorId()!=null)&&
                        (employeeModel.getJobId().getSupervisorId().getName()!="CEO")
                ){
                    this.employeeRepository.delete(employeeMapping.toEntity(employeeModel));
                    employeeModel.setJobId(employeeModel.getJobId().getSupervisorId());
                    this.insert(employeeModel);
                }
                map.put(employeeModel, e.getValue());
            }else{
                break;
            }
        }

        return map;
    }

    public LinkedHashMap<EmployeeModel, Float> getAveragePerformance(Date from, Date to){
        LinkedHashMap<EmployeeModel, Float> map = new LinkedHashMap<>();

        int i=0;
        int count = 0;
        float avg = (float)0.0;
        for(PerformanceEntity p : this.performanceRepository.findByIdIsNotNullOrderByEmployeeId()){
            if((p.getDate().compareTo(from)>=0)&&(p.getDate().compareTo(to)<=0)){
                if(p.getEmployeeId().getId()==i){

                    if(count>0) {
                        avg = (avg * count + p.getRating()) / (1 + count);
                    }else{
                        avg = p.getRating();
                    }

                    count++;
                    map.put(employeeMapping.toModel(p.getEmployeeId()),avg);
                }
                else{
                    i=p.getEmployeeId().getId();
                    avg = p.getRating();
                    count=1;
                    map.put(employeeMapping.toModel(p.getEmployeeId()),avg);
                }
            }
        }

        return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));


    }
}
