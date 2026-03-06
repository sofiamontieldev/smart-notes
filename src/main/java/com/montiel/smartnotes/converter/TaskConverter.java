package com.montiel.smartnotes.converter;

import com.montiel.smartnotes.dto.request.TaskRequestDTO;
import com.montiel.smartnotes.model.entity.Task;

public class TaskConverter {

    public static TaskRequestDTO taskToTaskDTO(Task task) {
        return new TaskRequestDTO(
                task.getId(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt()
        );
    }

    public static Task taskDTOToTask(TaskRequestDTO taskRequestDTO) {
        return Task.builder()
                .setId(taskRequestDTO.taskId())
                .setDescription(taskRequestDTO.description())
                .isCompleted(taskRequestDTO.complete())
                .build();
    }
}
