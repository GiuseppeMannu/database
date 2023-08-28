package com.database.mapping;

import com.database.entity.AddressEntity;
import com.database.model.AddressModel;

public class AddressMappingImplementation implements AddressMapping{

    public AddressModel toModel(AddressEntity addressEntity){
        AddressModel addressModel = new AddressModel();
        addressModel.setId(addressEntity.getId());
        addressModel.setCountry(addressEntity.getCountry());
        addressModel.setDivision(addressEntity.getDivision());
        addressModel.setCity(addressEntity.getCity());
        addressModel.setStreet(addressEntity.getStreet());
        addressModel.setNumber(addressEntity.getNumber());
        addressModel.setPostalCode(addressEntity.getPostalCode());
        return addressModel;
    }

    public AddressEntity toEntity(AddressModel addressModel){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressModel.getId());
        addressEntity.setCountry(addressModel.getCountry());
        addressEntity.setDivision(addressModel.getDivision());
        addressEntity.setCity(addressModel.getCity());
        addressEntity.setStreet(addressModel.getStreet());
        addressEntity.setNumber(addressModel.getNumber());
        addressEntity.setPostalCode(addressModel.getPostalCode());
        return addressEntity;
    }
}
