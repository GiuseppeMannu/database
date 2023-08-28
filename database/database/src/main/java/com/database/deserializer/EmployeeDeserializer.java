package com.database.deserializer;

import com.database.mapping.JobMappingImplementation;
import com.database.model.EmployeeModel;
import com.database.repository.JobRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class EmployeeDeserializer extends StdDeserializer<EmployeeModel> {

    @Autowired
    private JobRepository jobRepository;

    private final JobMappingImplementation jobMapping = new JobMappingImplementation();

    public EmployeeDeserializer() {
        this(null);
    }
    public EmployeeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public EmployeeModel deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        EmployeeModel employeeModel = new EmployeeModel();

        try {
            employeeModel.setId(node.get("id").asInt());
        }catch(NullPointerException n){
            employeeModel.setId(0);
        }

        try{
        employeeModel.setFirstName(node.get("firstName").asText());
        }catch(NullPointerException n){
            employeeModel.setFirstName(null);
        }

        try{
        employeeModel.setSecondName(node.get("secondName").asText());
        }catch(NullPointerException n){
            employeeModel.setSecondName(null);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            employeeModel.setHireDate(formatter.parse(node.get("hireDate").asText()));
        }catch(ParseException | NullPointerException e){
            employeeModel.setHireDate(null);
        }

        try{
        employeeModel.setJobId(jobMapping.toModel(jobRepository.getReferenceById((long)node.get("jobId").asInt())));
        }catch(NullPointerException e){
            employeeModel.setJobId(null);
        }

        return employeeModel;
    }
}
