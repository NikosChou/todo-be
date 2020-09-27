package com.ista.isp.assessment.todo.mappers;

import com.ista.isp.assessment.todo.model.StatusEntry;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntryEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusEntryConverter implements GenericConverter<StatusEntry, StatusEntryEntity>{

    @Override
    public StatusEntryEntity createFrom(StatusEntry dto) {
        final StatusEntryEntity statusEntryEntity = new StatusEntryEntity();
        return statusEntryEntity;
    }

    @Override
    public StatusEntry createFrom(StatusEntryEntity entity) {
        final StatusEntry statusEntry = new StatusEntry();
        statusEntry.setCreatedAt((entity.getCreatedAt()));
        statusEntry.setName((entity.getId().getStatusEntity().getName()));
        statusEntry.setStatusId((entity.getId().getStatusEntity().getId()));
        statusEntry.setEntityId((entity.getId().getItemEntity().getId()));
        return statusEntry;
    }

    @Override
    public StatusEntryEntity updateEntity(StatusEntryEntity entity, StatusEntry dto) {
        return entity;
    }
}
