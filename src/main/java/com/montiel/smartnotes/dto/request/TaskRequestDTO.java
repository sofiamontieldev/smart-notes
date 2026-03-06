package com.montiel.smartnotes.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskRequestDTO(
        UUID taskId,
        @NotBlank(message = "Description cannot be empty.")
        @Size(max = 255)
        String description,
        boolean complete,
        LocalDateTime createdAt
) {}
