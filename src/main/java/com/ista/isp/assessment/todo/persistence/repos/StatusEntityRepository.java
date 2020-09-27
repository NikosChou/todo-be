package com.ista.isp.assessment.todo.persistence.repos;

import com.ista.isp.assessment.todo.persistence.entities.StatusEntity;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

public interface StatusEntityRepository extends CrudRepository<StatusEntity, UUID> {

    List<StatusEntity> findAll();

    StatusEntity findByName(String status);

    default StatusEntity findByIdOrError(UUID status){
        return findById(status).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }
}
