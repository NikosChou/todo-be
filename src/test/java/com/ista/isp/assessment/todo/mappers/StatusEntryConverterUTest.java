package com.ista.isp.assessment.todo.mappers;

import com.ista.isp.assessment.todo.context.mappers.MapperConverterUTestContext;
import com.ista.isp.assessment.todo.model.StatusEntry;
import com.ista.isp.assessment.todo.persistence.entities.ItemEntity;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntity;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(MapperConverterUTestContext.class)
public class StatusEntryConverterUTest {

    @Autowired
    private StatusEntryConverter statusEntryConverter;

    @Test
    public void adtoToEntityShouldReturnEmptyStatusEntryEntity() {
        final LocalDateTime createdAt = LocalDateTime.now();
        StatusEntry statusEntry = new StatusEntry(createdAt, "status");
        final StatusEntryEntity entity = statusEntryConverter.createFrom(statusEntry);

        final StatusEntryEntity expected = new StatusEntryEntity();
        assertThat(entity).isEqualTo(expected);
    }
    
    @Test
    public void entityToAdtoShouldSetAllFields() {
        final StatusEntryEntity statusEntryEntity = new StatusEntryEntity();
        final LocalDateTime createdAt = LocalDateTime.now();
        statusEntryEntity.setCreatedAt(createdAt);
        final StatusEntity statusEntity = new StatusEntity();
        final UUID statusId = UUID.randomUUID();
        final String status = "status";
        statusEntity.setId(statusId);
        statusEntity.setName(status);
        final ItemEntity itemEntity = new ItemEntity();
        final UUID itemID = UUID.randomUUID();
        itemEntity.setId(itemID);

        final StatusEntryEntity.EmbeddableId embeddableId = new StatusEntryEntity.EmbeddableId();
        embeddableId.setStatusEntity(statusEntity);
        embeddableId.setItemEntity(itemEntity);

        embeddableId.setStatusEntity(statusEntity);
        statusEntryEntity.setId(embeddableId);

        final StatusEntry statusEntry = statusEntryConverter.createFrom(statusEntryEntity);
        assertThat(statusEntry).isEqualTo(new StatusEntry(statusId, itemID, createdAt, status));
    }

    @Test
    public void updateEntityShouldReturnEntityAsItIs() {
        assertThat(statusEntryConverter.updateEntity(new StatusEntryEntity(), null)).isEqualTo(new StatusEntryEntity());
    }

}
