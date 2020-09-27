package com.ista.isp.assessment.todo.persistence.repos;

import com.ista.isp.assessment.todo.persistence.entities.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemEntityRepository extends PagingAndSortingRepository<ItemEntity, UUID> {

    Page<ItemEntity> findAll(Pageable pageable);

    Optional<ItemEntity> findById(UUID id);

    default ItemEntity findByIdOrError(UUID id){
        return findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }
}
