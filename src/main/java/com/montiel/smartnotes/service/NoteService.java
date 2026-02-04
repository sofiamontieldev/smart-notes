package com.montiel.smartnotes.service;

import com.montiel.smartnotes.dto.NoteRequestDTO;
import com.montiel.smartnotes.dto.NoteResponseDTO;
import com.montiel.smartnotes.entity.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    NoteResponseDTO getNoteById(UUID id);
    NoteResponseDTO createNote(UUID userId, NoteRequestDTO noteDTO);
    Note updateNote(UUID noteId, Note note);
    void deleteNote(UUID noteId);
    List<NoteResponseDTO> getAllNotesByUserId(UUID userId);
}
