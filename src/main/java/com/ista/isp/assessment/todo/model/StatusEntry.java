package com.ista.isp.assessment.todo.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class StatusEntry extends AbstractDto {

    private UUID statusId;
    private UUID entityId;
    private LocalDateTime createdAt;
    private String name;

    public StatusEntry() {
    }

    public StatusEntry(LocalDateTime createdAt, String name) {
        this.createdAt = createdAt;
        this.name = name;
    }

    public StatusEntry(UUID statusId, UUID entityId, LocalDateTime createdAt, String name) {
        this.statusId = statusId;
        this.entityId = entityId;
        this.createdAt = createdAt;
        this.name = name;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntry that = (StatusEntry) o;
        return Objects.equals(statusId, that.statusId) &&
                Objects.equals(entityId, that.entityId) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, entityId, createdAt, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StatusEntry{");
        sb.append("statusId=").append(statusId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
}
