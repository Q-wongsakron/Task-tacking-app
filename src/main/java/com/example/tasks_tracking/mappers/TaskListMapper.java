package com.example.tasks_tracking.mappers;

import com.example.tasks_tracking.dto.TaskListDto;
import com.example.tasks_tracking.entity.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
