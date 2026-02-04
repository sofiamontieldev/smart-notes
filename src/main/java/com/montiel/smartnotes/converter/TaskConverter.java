package com.montiel.smartnotes.converter;

import com.montiel.smartnotes.dto.TaskResponseDTO;
import com.montiel.smartnotes.entity.Task;

public class TaskConverter {

    public static TaskResponseDTO taskToTaskDTO(Task task) {

        return new TaskResponseDTO(
                task.getId(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt()
        );
    }

    public static Task taskDTOToTask(TaskResponseDTO taskDTO) {

        return new Task.Builder()
                .setId(taskDTO.taskId())
                .setDescription(taskDTO.description())
                .isCompleted(taskDTO.complete())
                .build();
    }
}
