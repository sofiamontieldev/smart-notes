package com.montiel.smartnotes.service.api;

import com.montiel.smartnotes.dto.request.NoteRequestDTO;
import com.montiel.smartnotes.dto.response.NoteResponseDTO;
import com.montiel.smartnotes.model.entity.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    NoteResponseDTO getNoteById(UUID id);
    NoteResponseDTO getNoteByUserId(UUID noteId, UUID userId);
    NoteResponseDTO createNote(NoteRequestDTO noteDTO);
    Note updateNote(UUID noteId, Note note);
    void deleteNote(UUID noteId);
    List<NoteResponseDTO> getAllNotesByUserId(UUID userId);
}
