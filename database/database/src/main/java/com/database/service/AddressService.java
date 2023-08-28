package com.database.service;

import com.database.entity.AddressEntity;
import com.database.mapping.AddressMappingImplementation;
import com.database.model.AddressModel;
import com.database.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    private final AddressMappingImplementation mapping = new AddressMappingImplementation();

    public void insert (AddressModel addressModel){
        addressModel.setId(this.addressRepository.findFirstByOrderByIdDesc().getId()+1);
        AddressEntity addressEntity = mapping.toEntity(addressModel);
        try {
            this.addressRepository.save(addressEntity);
        }catch(DataIntegrityViolationException d){

            this.insert(addressModel);
        }
    }

    public List<AddressModel> getAll(){
        List<AddressModel> addressesModel = new ArrayList<>();

        for(AddressEntity j : this.addressRepository.findAll()){
            AddressModel addressModel = mapping.toModel(j);
            addressesModel.add(addressModel);
        }

        return addressesModel;
    }
}
