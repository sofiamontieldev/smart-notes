package com.montiel.smartnotes.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDTO(
        UUID taskId,
        String description,
        boolean complete,
        LocalDateTime createdAt
) {}