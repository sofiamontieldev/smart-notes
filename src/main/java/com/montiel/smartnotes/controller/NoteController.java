package com.montiel.smartnotes.controller;

import com.montiel.smartnotes.dto.NoteRequestDTO;
import com.montiel.smartnotes.dto.NoteResponseDTO;
import com.montiel.smartnotes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/notes")
public class NoteController {

    private final NoteService noteService;


    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<NoteResponseDTO> getNoteByUserId(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(noteService.getNoteById(userId));
    }

    @PostMapping
    public ResponseEntity<NoteResponseDTO> createNote(@PathVariable UUID userId,
                                                      @Valid @RequestBody NoteRequestDTO noteDTO) {
            return ResponseEntity.ok(noteService.createNote(userId, noteDTO));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getAllNotesByUserId(@PathVariable UUID userId) {
        List<NoteResponseDTO> notes = noteService.getAllNotesByUserId(userId);
        return  ResponseEntity.ok(notes);
    }


}
