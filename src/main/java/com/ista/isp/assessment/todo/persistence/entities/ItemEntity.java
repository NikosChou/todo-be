package com.ista.isp.assessment.todo.persistence.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ItemEntity extends BaseEntity<UUID> {

    @Id
    @NotNull
    private UUID id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    private LocalDateTime dueDate;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "id.itemEntity", cascade = CascadeType.ALL)
    private List<StatusEntryEntity> statusEntryEntities;

    public ItemEntity() {
    }

    public ItemEntity(@NotNull UUID id, @NotNull @NotBlank String title, @NotNull LocalDateTime dueDate, LocalDateTime createdAt, List<StatusEntryEntity> statusEntryEntities) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.statusEntryEntities = statusEntryEntities;
    }

    @Override
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

    public List<StatusEntryEntity> getStatusEntryEntities() {
        return statusEntryEntities;
    }

    public void setStatusEntryEntities(List<StatusEntryEntity> statusEntryEntities) {
        this.statusEntryEntities = statusEntryEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(dueDate, that.dueDate) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(statusEntryEntities, that.statusEntryEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, dueDate, createdAt, statusEntryEntities);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemEntity{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append("\'\n");
        sb.append(", dueDate=").append(dueDate);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", statusEntryEntities=").append(statusEntryEntities);
        sb.append('}');
        return sb.toString();
    }
}
