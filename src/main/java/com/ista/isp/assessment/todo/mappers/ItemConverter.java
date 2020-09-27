package com.ista.isp.assessment.todo.mappers;

import com.ista.isp.assessment.todo.model.Item;
import com.ista.isp.assessment.todo.model.StatusEntry;
import com.ista.isp.assessment.todo.persistence.entities.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ItemConverter implements GenericConverter<Item, ItemEntity> {

    @Autowired
    private StatusConverter statusConverter;

    @Override
    public ItemEntity createFrom(Item dto) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(UUID.randomUUID());
        itemEntity.setTitle(dto.getTitle());
        itemEntity.setDueDate(dto.getDueDate());
        itemEntity.setStatusEntryEntities(new ArrayList<>());
        return itemEntity;
    }

    @Override
    public Item createFrom(ItemEntity entity) {
        Item item = new Item();
        item.setId((entity.getId()));
        item.setTitle((entity.getTitle()));
        item.setDueDate((entity.getDueDate()));
        item.setCreatedAt((entity.getCreatedAt()));
        final List<StatusEntry> statuses = entity.getStatusEntryEntities().stream()
                .map(e -> new StatusEntry(e.getCreatedAt(), e.getId().getStatusEntity().getName()))
                .collect(Collectors.toList());
        item.setStatuses((statuses));
        return item;
    }

    @Override
    public ItemEntity updateEntity(ItemEntity itemEntity, Item dto) {
        itemEntity.setTitle(dto.getTitle());
        itemEntity.setDueDate(dto.getDueDate());
        return itemEntity;
    }
}
