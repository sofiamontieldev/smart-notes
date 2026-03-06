package com.montiel.smartnotes.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "note_id", nullable = false)
    private Note note;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Task(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.completed = builder.completed;
        this.note = builder.note;
    }

    public static class Builder {
        private UUID id;
        private String description;
        private boolean completed;
        private Note note;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder isCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder setNote(Note note) {
            this.note = note;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
