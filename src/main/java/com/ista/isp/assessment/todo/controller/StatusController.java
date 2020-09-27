package com.ista.isp.assessment.todo.controller;

import com.ista.isp.assessment.todo.logic.StatusEntityLogicBean;
import com.ista.isp.assessment.todo.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusEntityLogicBean statusEntityLogicBean;

    @GetMapping
    public List<Status> getAll() {
        return statusEntityLogicBean.findAll();
    }

    @PostMapping
    public Status create(@RequestBody Status status) {
        return statusEntityLogicBean.create(status);
    }

    @PutMapping("/{id}")
    public Status update(@PathVariable UUID id, @RequestBody Status status) {
        return statusEntityLogicBean.update(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        statusEntityLogicBean.delete(id);
    }
}
