package com.montiel.smartnotes.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record NoteRequestDTO(
        UUID userId,
        @NotBlank(message = "Title cannot be empty")
        @Size(max = 100, message = "Title is too long")
        String title,

        List<TaskRequestDTO> tasks) {
}
