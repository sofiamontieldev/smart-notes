package com.montiel.smartnotes.converter;

import com.montiel.smartnotes.dto.response.NoteResponseDTO;
import com.montiel.smartnotes.dto.response.UserResponseDTO;
import com.montiel.smartnotes.model.entity.User;

import java.util.List;

public class UserConverter {

    public static UserResponseDTO userToUserDTO(User user) {

        if (user == null) return null;

        List<NoteResponseDTO> notesDTO =(user.getNotes() == null)
                ? List.of()
                : user.getNotes().stream()
                .map(NoteConverter::noteToNoteDTO)
                .toList();

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                notesDTO
        );
    }

}
