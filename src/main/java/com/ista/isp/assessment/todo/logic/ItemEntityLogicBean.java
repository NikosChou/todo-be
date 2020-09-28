package com.ista.isp.assessment.todo.logic;

import com.ista.isp.assessment.todo.enums.ItemStatusEnum;
import com.ista.isp.assessment.todo.mappers.ItemConverter;
import com.ista.isp.assessment.todo.model.Item;
import com.ista.isp.assessment.todo.persistence.entities.ItemEntity;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntity;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntryEntity;
import com.ista.isp.assessment.todo.persistence.repos.ItemEntityRepository;
import com.ista.isp.assessment.todo.persistence.repos.StatusEntityRepository;
import com.ista.isp.assessment.todo.persistence.repos.StatusEntryEntityRepository;
import com.ista.isp.assessment.todo.response.ItemsWebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemEntityLogicBean {

    @Autowired
    private ItemEntityRepository itemEntityRepository;

    @Autowired
    private ItemConverter itemConverter;

    @Autowired
    private StatusEntityRepository statusEntityRepository;

    @Autowired
    private StatusEntryEntityRepository statusEntryEntityRepository;

    public Item findOne(UUID id) {
        return itemConverter.createFrom(itemEntityRepository.findByIdOrError(id));
    }

    public ItemsWebResponse findAll(Optional<List<Integer>> statuses, Integer page, Integer elements) {
        final Page<ItemEntity> pages = itemEntityRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, elements));
        final List<Item> items = pages.stream()
                .map(itemConverter::createFrom)
                .collect(Collectors.toList());
        return new ItemsWebResponse(pages.getTotalElements(), pages.getTotalPages(), pages.getNumber(), items);
    }

    public Item createNew(Item item) {
        final ItemEntity itemEntity = itemConverter.createFrom(item);
        final StatusEntity statusEntityNew = statusEntityRepository.findByName(ItemStatusEnum.CREATED.getValue());

        final StatusEntryEntity statusEntryEntity = new StatusEntryEntity();
        final StatusEntryEntity.EmbeddableId embeddableId = new StatusEntryEntity.EmbeddableId();
        embeddableId.setStatusEntity(statusEntityNew);
        embeddableId.setItemEntity(itemEntity);
        statusEntryEntity.setId(embeddableId);

        final ItemEntity savedItemEntity = itemEntityRepository.save(itemEntity);
        final StatusEntryEntity savedStatusEntryEntity = statusEntryEntityRepository.save(statusEntryEntity);

        savedItemEntity.getStatusEntryEntities().add(savedStatusEntryEntity);

        return itemConverter.createFrom(savedItemEntity);
    }

    public Item updateOne(UUID id, Item item) {
        final ItemEntity entity = itemEntityRepository.findByIdOrError(id);
        final ItemEntity updatedItemEntity = itemConverter.updateEntity(entity, item);
        final ItemEntity savedItemEntity = itemEntityRepository.save(updatedItemEntity);
        return itemConverter.createFrom(savedItemEntity);
    }

    public void delete(UUID id) {
        itemEntityRepository.deleteById(id);
    }
}
