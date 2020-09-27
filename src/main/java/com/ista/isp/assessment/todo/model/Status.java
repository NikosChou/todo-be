package com.ista.isp.assessment.todo.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Status extends AbstractDto {

    private UUID id;
    private LocalDateTime createdAt;
    private String name;

    public Status() {
    }

    public Status(UUID id, LocalDateTime createdAt, String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        Status status = (Status) o;
        return Objects.equals(id, status.id) &&
                Objects.equals(createdAt, status.createdAt) &&
                Objects.equals(name, status.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Status{");
        sb.append("id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
}
