package com.montiel.smartnotes.converter;

import com.montiel.smartnotes.dto.response.NoteResponseDTO;
import com.montiel.smartnotes.dto.request.TaskRequestDTO;
import com.montiel.smartnotes.model.entity.Note;

import java.util.List;

public class NoteConverter {

    public static NoteResponseDTO noteToNoteDTO(Note note) {

        List<TaskRequestDTO> tasksDTO = (note.getTasks() == null)
                ? List.of()
                : note.getTasks().stream()
                .map(TaskConverter::taskToTaskDTO)
                .toList();

        return new NoteResponseDTO(
                note.getId(),
                note.getTitle(),
                note.getCreatedAt(),
                note.getUser().getId(),
                tasksDTO
        );
    }

}
