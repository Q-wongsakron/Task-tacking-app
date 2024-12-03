package com.example.tasks_tracking.dto;

import com.example.tasks_tracking.entity.TaskPriority;
import com.example.tasks_tracking.entity.TaskStatus;

import java.time.LocalDateTime;

// provides a concise way to create an immutable data class with all of the boilerplate code
public record TaskDto(
    // java record automatically get data method and immutability
        Long id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {

}
