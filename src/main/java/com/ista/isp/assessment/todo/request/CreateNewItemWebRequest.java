package com.ista.isp.assessment.todo.request;

import com.ista.isp.assessment.todo.model.Item;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateNewItemWebRequest {

    @NotNull(message = "Title must not be null")
    private String title;
    @NotNull
    @FutureOrPresent(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    public CreateNewItemWebRequest() {
    }

    public CreateNewItemWebRequest(String title, LocalDateTime dueDate) {
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
        return new Item(null, getTitle(), getDueDate(), null, null);
    }
}