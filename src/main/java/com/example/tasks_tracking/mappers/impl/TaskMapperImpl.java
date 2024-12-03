package com.example.tasks_tracking.mappers.impl;

import com.example.tasks_tracking.dto.TaskDto;
import com.example.tasks_tracking.entity.Task;
import com.example.tasks_tracking.mappers.TaskMapper;
import org.springframework.stereotype.Component;

// make this is a bean it would inject any dependencies that we declare
// and make this class a candidate for injection wherever it declared throughout the application
@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
