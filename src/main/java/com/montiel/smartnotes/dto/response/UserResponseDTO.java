package com.montiel.smartnotes.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponseDTO (
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt,
        List<NoteResponseDTO> notes) {

}
