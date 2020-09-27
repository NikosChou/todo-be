package com.ista.isp.assessment.todo.mappers;

import com.ista.isp.assessment.todo.context.mappers.MapperConverterUTestContext;
import com.ista.isp.assessment.todo.model.Item;
import com.ista.isp.assessment.todo.model.StatusEntry;
import com.ista.isp.assessment.todo.persistence.entities.ItemEntity;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntity;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@SpringJUnitConfig(MapperConverterUTestContext.class)
public class ItemConverterUTest {

    @Autowired
    private ItemConverter itemConverter;

    @Test
    public void adtoToEntityShouldSetNewIdAndTitleWithDueDateFromDto() {
        final Item item = createItem();

        final ItemEntity actual = createItemEntity(item.getDueDate(), item.getTitle());

        final ItemEntity expected = itemConverter.createFrom(item);
        actual.setId(expected.getId());
        assertThat(expected).isEqualTo(actual);
    }

    private Item createItem() {
        final Item item = new Item();
        final LocalDateTime itemDueDate = LocalDateTime.now();
        final String itemTitle = "title";

        item.setDueDate((itemDueDate));
        item.setTitle((itemTitle));
        return item;
    }

    private ItemEntity createItemEntity(LocalDateTime itemDueDate, String itemTitle) {
        final ItemEntity itemEntity = new ItemEntity();
        itemEntity.setDueDate(itemDueDate);
        itemEntity.setTitle(itemTitle);
        itemEntity.setStatusEntryEntities(new ArrayList<>());
        return itemEntity;
    }

    @Test
    public void entityToAdtoShouldSetAllFields() {
        final UUID id = UUID.randomUUID();
        final String title = "title";
        final LocalDateTime dueDate = LocalDateTime.now();
        final LocalDateTime createdAt = LocalDateTime.now();
        final ArrayList<StatusEntryEntity> statusEntryEntities = new ArrayList<>();

        final ItemEntity entity = new ItemEntity(id, title, dueDate, createdAt, statusEntryEntities);

        final StatusEntity statusEntity = createStatusEntity();
        final StatusEntryEntity statusEntryEntity = createStatusEntryEntity(entity, statusEntity);
        statusEntryEntities.add(statusEntryEntity);
        entity.setStatusEntryEntities(statusEntryEntities);

        final Item actual = createItemActual(title, dueDate, createdAt, id, statusEntity.getName(), statusEntryEntity.getCreatedAt());


        final Item expected = itemConverter.createFrom(entity);

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void updateEntityShouldUpdateOnlyTitleAndDueDate() {
        final ItemEntity itemEntity = new ItemEntity(UUID.randomUUID(), "title", LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>());
        final Item item = itemConverter.createFrom(itemEntity);
        final String newTitle = "new title";
        final LocalDateTime newDate =LocalDateTime.now().plusDays(2);
        item.setTitle(newTitle);
        item.setDueDate(newDate);

        final ItemEntity updateEntity = itemConverter.updateEntity(itemEntity, item);
        assertThat(updateEntity)
                .extracting(ItemEntity::getTitle, ItemEntity::getDueDate)
                .containsExactly(newTitle, newDate);
    }

    private static StatusEntryEntity createStatusEntryEntity(ItemEntity itemEntity, StatusEntity statusEntity) {
        final LocalDateTime statusEntryEntityCreatedAt = LocalDateTime.now();
        final StatusEntryEntity statusEntryEntity = new StatusEntryEntity();

        final StatusEntryEntity.EmbeddableId embeddableId = new StatusEntryEntity.EmbeddableId();
        embeddableId.setItemEntity(itemEntity);
        embeddableId.setStatusEntity(statusEntity);

        statusEntryEntity.setId(embeddableId);
        statusEntryEntity.setCreatedAt(statusEntryEntityCreatedAt);
        return statusEntryEntity;
    }

    private static StatusEntity createStatusEntity() {
        final LocalDateTime statusEntityCreatedAt = LocalDateTime.now();
        final UUID statusEntityID = UUID.randomUUID();
        final String statusEntityName = "status";

        final StatusEntity statusEntity = new StatusEntity();
        statusEntity.setCreatedAt(statusEntityCreatedAt);
        statusEntity.setId(statusEntityID);
        statusEntity.setName(statusEntityName);
        return statusEntity;
    }

    private static Item createItemActual(String title, LocalDateTime dueDate, LocalDateTime createdAt, UUID id, String statusEntityName, LocalDateTime statusEntryEntityCreatedAt) {
        final Item actual = new Item();
        actual.setTitle((title));
        actual.setDueDate((dueDate));
        actual.setCreatedAt((createdAt));
        actual.setId((id));

        final StatusEntry statusEntry = new StatusEntry();
        final ArrayList<StatusEntry> statusEntries = new ArrayList<>();
        actual.setStatuses(statusEntries);
        statusEntry.setName(statusEntityName);
        statusEntry.setCreatedAt(statusEntryEntityCreatedAt);
        statusEntries.add(statusEntry);

        return actual;
    }
}
