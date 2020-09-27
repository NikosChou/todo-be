package com.ista.isp.assessment.todo.controller;

import com.ista.isp.assessment.todo.logic.StatusEntryEntityLogicBean;
import com.ista.isp.assessment.todo.model.Item;
import com.ista.isp.assessment.todo.model.StatusEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/items/{item}/status")
public class StatusEntryController {

    @Autowired
    private StatusEntryEntityLogicBean statusEntryEntityLogicBean;

    @GetMapping
    public List<StatusEntry> getAllForItem(@PathVariable UUID item) {
        return statusEntryEntityLogicBean.findAllForItem(item);
    }

    @GetMapping("/{status}")
    public StatusEntry getAllForItem(@PathVariable UUID item, @PathVariable UUID status) {
        return statusEntryEntityLogicBean.findStatusForItem(item, status);
    }

    @PostMapping("/{status}")
    public Item addStatusToEntity(@PathVariable UUID item, @PathVariable UUID status) {
        return statusEntryEntityLogicBean.addStatusToItem(item, status);
    }

    @PostMapping
    public Item addStatusCompleteToEntity(@PathVariable UUID item, @RequestParam(value = "complete") Boolean completed) {
        if(completed) {
            return statusEntryEntityLogicBean.addStatusCompletedToItem(item);
        }
        return statusEntryEntityLogicBean.removeStatusCompletedToItem(item);
    }


}
