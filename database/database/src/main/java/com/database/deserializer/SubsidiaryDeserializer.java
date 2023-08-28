package com.database.deserializer;

import com.database.mapping.AddressMappingImplementation;
import com.database.mapping.EmployeeMappingImplementation;
import com.database.mapping.SubsidiaryMappingImplementation;
import com.database.model.SubsidiaryModel;
import com.database.repository.AddressRepository;
import com.database.repository.EmployeeRepository;
import com.database.repository.SubsidiaryRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.ParseException;

public class SubsidiaryDeserializer extends StdDeserializer<SubsidiaryModel> {

    @Autowired
    private SubsidiaryRepository subsidiaryRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private final SubsidiaryMappingImplementation subsidiaryMapping = new SubsidiaryMappingImplementation();
    private final AddressMappingImplementation addressMapping = new AddressMappingImplementation();
    private final EmployeeMappingImplementation employeeMapping = new EmployeeMappingImplementation();

    public SubsidiaryDeserializer() {
        this(null);
    }
    public SubsidiaryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SubsidiaryModel deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        SubsidiaryModel subsidiaryModel = new SubsidiaryModel();

        try {
            subsidiaryModel.setId(node.get("id").asInt());
        }catch(NullPointerException n){
            subsidiaryModel.setId(0);
        }

        try{
        subsidiaryModel.setName(node.get("name").asText());
        }catch(NullPointerException n){
            subsidiaryModel.setName(null);
        }

        try{
        subsidiaryModel.setAddress(this.addressMapping.toModel(this.addressRepository.getReferenceById((long)node.get("address").asInt())));
        }catch(NullPointerException n){
            subsidiaryModel.setAddress(null);
        }

        try {
            subsidiaryModel.setManager(this.employeeMapping.toModel(this.employeeRepository.getReferenceById((long)node.get("manager").asInt())));
        }catch(NullPointerException e){
            subsidiaryModel.setManager(null);
        }

        return subsidiaryModel;
    }
}
