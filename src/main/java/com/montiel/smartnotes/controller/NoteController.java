package com.montiel.smartnotes.controller;

import com.montiel.smartnotes.dto.request.NoteRequestDTO;
import com.montiel.smartnotes.dto.response.NoteResponseDTO;
import com.montiel.smartnotes.service.api.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{noteId}/users/{userId}")
    public ResponseEntity<NoteResponseDTO> getNoteByUserId(
            @PathVariable("noteId") UUID noteId,
            @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(noteService.getNoteByUserId(noteId, userId));
    }

    @PostMapping
    public ResponseEntity<NoteResponseDTO> createNote(@Valid @RequestBody NoteRequestDTO noteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(noteDTO));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<NoteResponseDTO>> getAllNotesByUserId(@PathVariable("userId") UUID userId) {
        List<NoteResponseDTO> notes = noteService.getAllNotesByUserId(userId);
        return ResponseEntity.ok(notes);
    }
}
