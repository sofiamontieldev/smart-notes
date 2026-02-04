package com.montiel.smartnotes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record NoteRequestDTO(
        @NotBlank(message = "Title cannot be empty")
        @Size(max = 100, message = "Title is too long")
        String title,

        List<String> tasks) {
}
