package com.ista.isp.assessment.todo.controller;

import com.ista.isp.assessment.todo.logic.ItemEntityLogicBean;
import com.ista.isp.assessment.todo.request.CreateNewItemWebRequest;
import com.ista.isp.assessment.todo.request.UpdateItemWebRequest;
import com.ista.isp.assessment.todo.response.ItemWebResponse;
import com.ista.isp.assessment.todo.response.ItemsWebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemEntityLogicBean itemEntityLogicBean;

    @GetMapping
    public ItemsWebResponse getItems(@RequestParam(value = "p", defaultValue = "0") Integer page,
                                     @RequestParam(value = "e", defaultValue = "10") Integer elements) {
        return itemEntityLogicBean.findAll(page, elements);
    }

    @PostMapping
    public ItemWebResponse saveNewItem(@Valid @RequestBody CreateNewItemWebRequest request) {
        return new ItemWebResponse(itemEntityLogicBean.createNew(request.toDTO()));
    }

    @PutMapping("/{id}")
    public ItemWebResponse updateItem(@PathVariable UUID id, @Valid @RequestBody UpdateItemWebRequest request) {
        return new ItemWebResponse(itemEntityLogicBean.updateOne(id, request.toDTO()));
    }

    @GetMapping("/{id}")
    public ItemWebResponse getWithId(@PathVariable UUID id) {
        return new ItemWebResponse(itemEntityLogicBean.findOne(id));
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable UUID id) {
        itemEntityLogicBean.delete(id);
    }

}
