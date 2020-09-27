package com.ista.isp.assessment.todo.persistence.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class StatusEntryEntity extends BaseEntity<StatusEntryEntity.EmbeddableId> {

    @Embeddable
    public static class EmbeddableId implements Serializable {
        @ManyToOne
        @JoinColumn(name="item_entity_id", nullable=false)
        private ItemEntity itemEntity;
        @ManyToOne
        @JoinColumn(name="status_entity_id", nullable=false)
        private StatusEntity statusEntity;

        public ItemEntity getItemEntity() {
            return itemEntity;
        }

        public void setItemEntity(ItemEntity itemEntity) {
            this.itemEntity = itemEntity;
        }

        public StatusEntity getStatusEntity() {
            return statusEntity;
        }

        public void setStatusEntity(StatusEntity statusEntity) {
            this.statusEntity = statusEntity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EmbeddableId that = (EmbeddableId) o;
            return Objects.equals(itemEntity, that.itemEntity) &&
                    Objects.equals(statusEntity, that.statusEntity);
        }

        @Override
        public int hashCode() {
            return Objects.hash(itemEntity, statusEntity);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("EmbeddableId{");
            sb.append("itemEntity=").append(itemEntity.getId());
            sb.append(", statusEntity=").append(statusEntity.getId());
            sb.append('}');
            return sb.toString();
        }
    }

    @EmbeddedId
    private EmbeddableId id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public EmbeddableId getId() {
        return id;
    }

    public void setId(EmbeddableId id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntryEntity that = (StatusEntryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StatusEntryEntity{");
        sb.append("id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
