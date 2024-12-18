package com.example.tasks_tracking.dto;

import java.util.List;

public record TaskListDto(
        Long id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks)
{

}
