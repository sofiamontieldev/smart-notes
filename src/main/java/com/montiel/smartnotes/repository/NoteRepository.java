package com.montiel.smartnotes.repository;

import com.montiel.smartnotes.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {

    List<Note> findByUserId(UUID userId);
    Optional<Note> findByIdAndUserId(UUID noteId, UUID userId);
}
