package com.ista.isp.assessment.todo.request;

import com.ista.isp.assessment.todo.model.Item;

import java.time.LocalDateTime;

public class UpdateItemWebRequest {

    private String title;

    private LocalDateTime dueDate;

    public UpdateItemWebRequest(String title, LocalDateTime dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Item toDTO() {
        Item item = new Item();
        item.setTitle(getTitle());
        item.setDueDate(getDueDate());
        return item;
    }
}