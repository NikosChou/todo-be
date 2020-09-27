package com.ista.isp.assessment.todo.mappers;

import com.ista.isp.assessment.todo.model.Status;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusConverter implements GenericConverter<Status, StatusEntity> {

    @Override
    public StatusEntity createFrom(Status status) {
        final StatusEntity statusEntity = new StatusEntity();
        statusEntity.setName(status.getName());
        return statusEntity;
    }

    @Override
    public Status createFrom(StatusEntity entity) {
        final Status status = new Status();
        status.setId((entity.getId()));
        status.setName((entity.getName()));
        status.setCreatedAt((entity.getCreatedAt()));
        return status;
    }

    @Override
    public StatusEntity updateEntity(StatusEntity statusEntity, Status status) {
        statusEntity.setName(status.getName());
        return statusEntity;
    }
}
