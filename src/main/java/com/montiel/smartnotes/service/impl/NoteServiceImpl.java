package com.montiel.smartnotes.service.impl;

import com.montiel.smartnotes.converter.NoteConverter;
import com.montiel.smartnotes.dto.NoteRequestDTO;
import com.montiel.smartnotes.dto.NoteResponseDTO;
import com.montiel.smartnotes.entity.Note;
import com.montiel.smartnotes.entity.Task;
import com.montiel.smartnotes.entity.User;
import com.montiel.smartnotes.exception.BadRequestException;
import com.montiel.smartnotes.exception.NotFoundException;
import com.montiel.smartnotes.repository.UserRepository;
import com.montiel.smartnotes.service.NoteService;
import com.montiel.smartnotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {

    NoteRepository noteRepository;
    UserRepository userRepository;

    public NoteServiceImpl(NoteRepository repository) {
        this.noteRepository = repository;
    }

    @Override
    public NoteResponseDTO getNoteById(UUID id) {
        validateNoteId(id);
        return noteRepository.findById(id)
                .map(NoteConverter::noteToNoteDTO)
                .orElseThrow(()-> new NotFoundException("Note not found with id " + id));
    }

    @Override
    public NoteResponseDTO createNote(UUID userId, NoteRequestDTO noteDTO) {
        validateUserID(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        Note newNote = Note.builder()
                .setUser(user)
                .setTitle(noteDTO.title())
                .build();

        if(noteDTO.tasks() != null) {
            List<Task> tasks = noteDTO.tasks().stream()
                    .map(newTasks -> Task.builder()
                            .setDescription(newTasks)
                            .isCompleted(false)
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
        validateUserID(userId);
        List<Note> notes = noteRepository.findByUserId(userId);

        return notes.stream()
                .map(NoteConverter::noteToNoteDTO)
                .toList();
    }

    private void validateUserID(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new BadRequestException("User not found");
        }
    }

    private void validateNoteId(UUID id) throws BadRequestException {
        if (id == null) {
            throw new BadRequestException("Note ID cannot be null");
        }
    }


}
