package com.montiel.smartnotes.service.impl;

import com.montiel.smartnotes.converter.NoteConverter;
import com.montiel.smartnotes.dto.request.NoteRequestDTO;
import com.montiel.smartnotes.dto.response.NoteResponseDTO;
import com.montiel.smartnotes.exception.BadRequestException;
import com.montiel.smartnotes.exception.NotFoundException;
import com.montiel.smartnotes.model.entity.Note;
import com.montiel.smartnotes.model.entity.Task;
import com.montiel.smartnotes.model.entity.User;
import com.montiel.smartnotes.repository.NoteRepository;
import com.montiel.smartnotes.repository.UserRepository;
import com.montiel.smartnotes.service.api.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NoteResponseDTO getNoteById(UUID id) {
        validateNoteId(id);
        return noteRepository.findById(id)
                .map(NoteConverter::noteToNoteDTO)
                .orElseThrow(() -> new NotFoundException("Note not found with id " + id));
    }

    @Override
    public NoteResponseDTO getNoteByUserId(UUID noteId, UUID userId) {
        validateNoteId(noteId);
        validateUserId(userId);

        return noteRepository.findByIdAndUserId(noteId, userId)
                .map(NoteConverter::noteToNoteDTO)
                .orElseThrow(() -> new NotFoundException("Note not found with id " + noteId + " for user " + userId));
    }

    @Override
    public NoteResponseDTO createNote(NoteRequestDTO noteDTO) {
        validateNoteRequest(noteDTO);
        validateUserId(noteDTO.userId());

        User user = userRepository.findById(noteDTO.userId())
                .orElseThrow(() -> new BadRequestException("User not found"));

        Note newNote = Note.builder()
                .setUser(user)
                .setTitle(noteDTO.title())
                .build();

        if (noteDTO.tasks() != null) {
            List<Task> tasks = noteDTO.tasks().stream()
                    .filter(Objects::nonNull)
                    .map(newTask -> Task.builder()
                            .setDescription(newTask.description())
                            .isCompleted(newTask.complete())
                            .setNote(newNote)
                            .build()
                    )
                    .toList();

            newNote.setTasks(tasks);
        }

        Note savedNote = noteRepository.save(newNote);

        return NoteConverter.noteToNoteDTO(savedNote);
    }

    @Override
    public Note updateNote(UUID noteId, Note note) {
        return null;
    }

    @Override
    public void deleteNote(UUID noteId) {
    }

    @Override
    public List<NoteResponseDTO> getAllNotesByUserId(UUID userId) {
        validateUserId(userId);
        List<Note> notes = noteRepository.findByUserId(userId);

        return notes.stream()
                .map(NoteConverter::noteToNoteDTO)
                .toList();
    }

    private void validateUserId(UUID userId) {
        if (userId == null) {
            throw new BadRequestException("User ID cannot be null");
        }

        if (!userRepository.existsById(userId)) {
            throw new BadRequestException("User not found");
        }
    }

    private void validateNoteId(UUID id) {
        if (id == null) {
            throw new BadRequestException("Note ID cannot be null");
        }
    }

    private void validateNoteRequest(NoteRequestDTO noteDTO) {
        if (noteDTO == null) {
            throw new BadRequestException("Note payload cannot be null");
        }
    }

}
