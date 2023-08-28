package com.database.mapping;

import com.database.entity.AddressEntity;
import com.database.entity.JobEntity;
import com.database.model.AddressModel;
import com.database.model.JobModel;

public interface AddressMapping {
    AddressModel toModel(AddressEntity addressEntity);

    AddressEntity toEntity(AddressModel addressModel);
}
