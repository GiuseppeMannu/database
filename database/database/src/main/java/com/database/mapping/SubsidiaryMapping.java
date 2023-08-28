package com.database.mapping;

import com.database.entity.SubsidiaryEntity;
import com.database.model.SubsidiaryModel;

public interface SubsidiaryMapping {
    SubsidiaryModel toModel(SubsidiaryEntity subsidiaryEntity);

    SubsidiaryEntity toEntity(SubsidiaryModel subsidiaryModel);
}
