package com.database.controller;

import com.database.model.AddressModel;
import com.database.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private final AddressService addressService = new AddressService();

    @GetMapping("/addresses")
    public List<AddressModel> getAll(){
        return addressService.getAll();
    }

    @PostMapping(
            path = "/addresses/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void insert(
            @RequestBody AddressModel addressModel
    ){
        addressService.insert(addressModel);
    }

}
