package com.ista.isp.assessment.todo.mappers;

import com.ista.isp.assessment.todo.model.AbstractDto;
import com.ista.isp.assessment.todo.persistence.entities.BaseEntity;

public interface GenericConverter<D extends AbstractDto, E extends BaseEntity> {
    E createFrom(D dto);

    D createFrom(E entity);

    E updateEntity(E entity, D dto);
}
