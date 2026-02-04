package com.montiel.smartnotes.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NoteResponseDTO(
        UUID noteId,
        String title,
        LocalDateTime createdAt,
        UUID userId,
        List<TaskResponseDTO> tasks) {
}
