package com.database.deserializer;

import com.database.mapping.JobMappingImplementation;
import com.database.model.JobModel;
import com.database.repository.JobRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class JobDeserializer extends StdDeserializer<JobModel> {

    @Autowired
    private JobRepository jobRepository;

    private final JobMappingImplementation jobMapping = new JobMappingImplementation();

    public JobDeserializer() {
        this(null);
    }
    public JobDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public JobModel deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        JobModel jobModel = new JobModel();

        try {
            jobModel.setId(node.get("id").asInt());
        }catch(NullPointerException n){
            jobModel.setId(0);
        }

        try{
        jobModel.setName(node.get("name").asText());
        }catch(NullPointerException n){
            jobModel.setName(null);
        }

        try{
        jobModel.setSalary(Float.parseFloat(node.get("salary").asText()));
        }catch(NullPointerException n){
            jobModel.setSalary((float)0.0);
        }

        try{
            jobModel.setSupervisorId(this.jobMapping.toModel(this.jobRepository.getReferenceById((long)node.get("supervisorId").asInt())));
        }catch(NullPointerException n){
            jobModel.setSupervisorId(null);
        }

        return jobModel;
    }
}
