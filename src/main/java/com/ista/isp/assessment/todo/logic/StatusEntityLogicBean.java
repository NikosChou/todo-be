package com.ista.isp.assessment.todo.logic;

import com.ista.isp.assessment.todo.mappers.StatusConverter;
import com.ista.isp.assessment.todo.model.Status;
import com.ista.isp.assessment.todo.persistence.entities.StatusEntity;
import com.ista.isp.assessment.todo.persistence.repos.StatusEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatusEntityLogicBean {

    @Autowired
    private StatusEntityRepository statusEntityRepository;

    @Autowired
    private StatusConverter statusConverter;

    public List<Status> findAll() {
        return statusEntityRepository.findAll().stream().map(statusConverter::createFrom).collect(Collectors.toList());
    }

    public Status create(Status status) {
        final StatusEntity savedStatusEntity = statusEntityRepository.save(statusConverter.createFrom(status));
        return statusConverter.createFrom(savedStatusEntity);
    }

    public void delete(UUID status) {
        statusEntityRepository.deleteById(status);
    }

    public Status update(UUID id, Status status) {
        final StatusEntity statusEntity = statusEntityRepository.findByIdOrError(id);
        final StatusEntity updateEntity = statusConverter.updateEntity(statusEntity, status);
        return statusConverter.createFrom(statusEntityRepository.save(updateEntity));
    }
}
