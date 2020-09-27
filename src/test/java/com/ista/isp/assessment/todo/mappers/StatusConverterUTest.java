package com.ista.isp.assessment.todo.mappers;

import com.ista.isp.assessment.todo.context.mappers.MapperConverterUTestContext;
import com.ista.isp.assessment.todo.model.Status;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(MapperConverterUTestContext.class)
public class StatusConverterUTest {
    @Autowired
    private StatusConverter statusConverter;

    @Test
    public void adtoToEntityShouldSetOnlyName() {
        final String statusName = "name";
        Status status = new Status();
        status.setName(statusName);

        final StatusEntity statusEntity = statusConverter.createFrom(status);

         assertThat(statusEntity).isEqualTo(new StatusEntity(statusName));
    }

    @Test
    public void entityToAdtoShouldSetAllFields() {
        final UUID id = UUID.randomUUID();
        final LocalDateTime createdAt = LocalDateTime.now();
        final String name = "name";
        final StatusEntity statusEntity = new StatusEntity(id, createdAt, name);
        final Status status = statusConverter.createFrom(statusEntity);

        assertThat(status).isEqualTo(new Status(id, createdAt, name));
    }

    @Test
    public void updateEntityShouldUpdateOnlyName() {
        final StatusEntity statusEntity = new StatusEntity(UUID.randomUUID(), LocalDateTime.now(), "name");
        final Status status = statusConverter.createFrom(statusEntity);
        final String newName = "new name";
        status.setName(newName);

        final StatusEntity updateEntity = statusConverter.updateEntity(statusEntity, status);

        statusEntity.setName(newName);
        assertThat(updateEntity).isEqualTo(statusEntity);
    }
}
