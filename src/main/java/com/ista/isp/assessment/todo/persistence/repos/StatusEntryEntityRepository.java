package com.ista.isp.assessment.todo.persistence.repos;

import com.ista.isp.assessment.todo.persistence.entities.StatusEntryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StatusEntryEntityRepository extends CrudRepository<StatusEntryEntity, StatusEntryEntity.EmbeddableId> {
    List<StatusEntryEntity> findAll();

    List<StatusEntryEntity> findAllByIdItemEntityId(UUID itemId);

    Optional<StatusEntryEntity> findByIdItemEntityIdAndIdStatusEntityId(UUID itemId, UUID status);

    default StatusEntryEntity findByIdItemEntityIdAndIdStatusEntityIdOrError(UUID itemId, UUID status) {
        return findByIdItemEntityIdAndIdStatusEntityId(itemId, status).orElseThrow(() -> new EntityNotFoundException(itemId.toString()));
    }
}
