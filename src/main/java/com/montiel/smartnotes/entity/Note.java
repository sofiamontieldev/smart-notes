package com.montiel.smartnotes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    private Note(Builder builder) {
        this.title = builder.title;
        this.user = builder.user;
        this.tasks = builder.tasks != null ? builder.tasks : new ArrayList<>();
    }

    public static class Builder {
        private String title;
        private User user;
        private List<Task> tasks;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setTasks(List<Task> tasks) {
            this.tasks = tasks;
            return this;
        }

        public Note build() {
            return new Note(this);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

}
