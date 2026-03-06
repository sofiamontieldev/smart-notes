package com.montiel.smartnotes.dto.response;

import com.montiel.smartnotes.dto.request.TaskRequestDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NoteResponseDTO(
        UUID noteId,
        String title,
        LocalDateTime createdAt,
        UUID userId,
        List<TaskRequestDTO> tasks) {
}
