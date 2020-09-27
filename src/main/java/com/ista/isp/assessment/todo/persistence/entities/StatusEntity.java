package com.ista.isp.assessment.todo.persistence.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class StatusEntity extends BaseEntity<UUID> {

    @Id
    @GeneratedValue
    private UUID id;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @NotNull
    private String name;

    public StatusEntity() {
    }

    public StatusEntity(UUID id, LocalDateTime createdAt, @NotNull String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
    }

    public StatusEntity(@NotNull String name) {
        this.name = name;
    }

    @Override
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
        StatusEntity that = (StatusEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StatusEntity{");
        sb.append("id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", name='").append(name).append("\'\n");
        sb.append('}');
        return sb.toString();
    }
}
