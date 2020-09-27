package com.ista.isp.assessment.todo.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Item extends AbstractDto {

    private UUID id;
    private String title;
    private LocalDateTime dueDate;
    private List<StatusEntry> statuses;
    private LocalDateTime createdAt;

    public Item() {
    }

    public Item(UUID id, String title, LocalDateTime dueDate, List<StatusEntry> statuses, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.statuses = statuses;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<StatusEntry> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<StatusEntry> statuses) {
        this.statuses = statuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(title, item.title) &&
                Objects.equals(dueDate, item.dueDate) &&
                Objects.equals(statuses, item.statuses) &&
                Objects.equals(createdAt, item.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, dueDate, statuses, createdAt);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", dueDate=").append(dueDate);
        sb.append(", statuses=").append(statuses);
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
