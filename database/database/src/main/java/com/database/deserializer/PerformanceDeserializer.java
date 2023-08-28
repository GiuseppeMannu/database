package com.database.deserializer;

import com.database.mapping.EmployeeMappingImplementation;
import com.database.mapping.PerformanceMappingImplementation;
import com.database.model.PerformanceModel;
import com.database.repository.EmployeeRepository;
import com.database.repository.PerformanceRepository;
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

public class PerformanceDeserializer extends StdDeserializer<PerformanceModel> {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private final PerformanceMappingImplementation performanceMapping = new PerformanceMappingImplementation();

    private final EmployeeMappingImplementation employeeMapping = new EmployeeMappingImplementation();
    public PerformanceDeserializer() {
        this(null);
    }
    public PerformanceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PerformanceModel deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        PerformanceModel performanceModel = new PerformanceModel();

        try {
            performanceModel.setId(node.get("id").asInt());
        }catch(NullPointerException n){
            performanceModel.setId(0);
        }

        try {
            performanceModel.setEmployeeId(employeeMapping.toModel(employeeRepository.getReferenceById((long)node.get("employeeId").asInt())));
        }catch(NullPointerException n){
            performanceModel.setEmployeeId(null);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            performanceModel.setDate(formatter.parse(node.get("date").asText()));
        }catch(ParseException | NullPointerException n){
            performanceModel.setDate(null);
        }

        try {
            performanceModel.setRating(node.get("rating").asInt());
        }catch(NullPointerException n){
            performanceModel.setRating(0);
        }

        return performanceModel;
    }
}
