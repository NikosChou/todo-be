package com.ista.isp.assessment.todo.logic;

import com.ista.isp.assessment.todo.enums.ItemStatusEnum;
import com.ista.isp.assessment.todo.mappers.ItemConverter;
import com.ista.isp.assessment.todo.mappers.StatusEntryConverter;
import com.ista.isp.assessment.todo.model.Item;
import com.ista.isp.assessment.todo.model.StatusEntry;
import com.ista.isp.assessment.todo.persistence.entities.ItemEntity;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntity;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntryEntity;
import com.ista.isp.assessment.todo.persistence.repos.ItemEntityRepository;
import com.ista.isp.assessment.todo.persistence.repos.StatusEntityRepository;
import com.ista.isp.assessment.todo.persistence.repos.StatusEntryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatusEntryEntityLogicBean {

    @Autowired
    private StatusEntryEntityRepository statusEntryEntityRepository;

    @Autowired
    private ItemEntityRepository itemEntityRepository;

    @Autowired
    private StatusEntityRepository statusEntityRepository;

    @Autowired
    private StatusEntryConverter statusEntryConverter;

    @Autowired
    private ItemConverter itemConverter;

    public List<StatusEntry> findAllForItem(UUID itemId) {
        return statusEntryEntityRepository.findAllByIdItemEntityId(itemId)
                .stream()
                .map(statusEntryConverter::createFrom)
                .collect(Collectors.toList());
    }

    public StatusEntry findStatusForItem(UUID itemId, UUID status) {
        return statusEntryConverter.createFrom(statusEntryEntityRepository.findByIdItemEntityIdAndIdStatusEntityIdOrError(itemId, status));
    }

    public Item addStatusToItem(UUID item, UUID status) {
        final StatusEntryEntity statusEntryEntity = new StatusEntryEntity();
        final StatusEntryEntity.EmbeddableId id = new StatusEntryEntity.EmbeddableId();
        id.setItemEntity(itemEntityRepository.findByIdOrError(item));
        id.setStatusEntity(statusEntityRepository.findByIdOrError(status));
        statusEntryEntity.setId(id);
        statusEntryEntity.setCreatedAt(LocalDateTime.now());
        statusEntryEntityRepository.save(statusEntryEntity);
        return itemConverter.createFrom(itemEntityRepository.findByIdOrError(item));
    }

    public Item addStatusCompletedToItem(UUID item) {
        final StatusEntryEntity statusEntryEntity = new StatusEntryEntity();
        final StatusEntryEntity.EmbeddableId id = new StatusEntryEntity.EmbeddableId();
        id.setItemEntity(itemEntityRepository.findByIdOrError(item));
        id.setStatusEntity(statusEntityRepository.findByName(ItemStatusEnum.COMPLETED.getValue()));
        statusEntryEntity.setId(id);
        statusEntryEntity.setCreatedAt(LocalDateTime.now());
        statusEntryEntityRepository.save(statusEntryEntity);
        return itemConverter.createFrom(itemEntityRepository.findByIdOrError(item));
    }

    public Item removeStatusCompletedToItem(UUID item) {
        final StatusEntity completed = statusEntityRepository.findByName(ItemStatusEnum.COMPLETED.getValue());
        final ItemEntity itemEntity = itemEntityRepository.findByIdOrError(item);
        final StatusEntryEntity.EmbeddableId id = new StatusEntryEntity.EmbeddableId();
        id.setStatusEntity(completed);
        id.setItemEntity(itemEntity);
        statusEntryEntityRepository.deleteById(id);
        return itemConverter.createFrom(itemEntityRepository.findByIdOrError(item));
    }
}
