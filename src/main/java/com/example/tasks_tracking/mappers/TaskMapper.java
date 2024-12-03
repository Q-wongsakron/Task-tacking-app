package com.example.tasks_tracking.mappers;

import com.example.tasks_tracking.dto.TaskDto;
import com.example.tasks_tracking.entity.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
