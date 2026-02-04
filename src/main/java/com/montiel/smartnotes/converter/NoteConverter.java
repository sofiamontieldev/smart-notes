package com.montiel.smartnotes.converter;

import com.montiel.smartnotes.dto.NoteResponseDTO;
import com.montiel.smartnotes.dto.TaskResponseDTO;
import com.montiel.smartnotes.entity.Note;
import com.montiel.smartnotes.entity.Task;

import java.util.List;

public class NoteConverter {

    public static NoteResponseDTO noteToNoteDTO(Note note) {

        List<TaskResponseDTO> tasksDTO = (note.getTasks() == null)
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
