package com.example.tasks_tracking.controller;


import com.example.tasks_tracking.dto.TaskListDto;
import com.example.tasks_tracking.entity.TaskList;
import com.example.tasks_tracking.mappers.TaskListMapper;
import com.example.tasks_tracking.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @CrossOrigin
    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                // .map is convert task list to a task list dto
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        // take a task list dto in the request body
        TaskList createdTaskList = taskListService.createTaskList(
                // convert it into a task list and save to database with service
                taskListMapper.fromDto(taskListDto)
        );
        // final return convert back to a dto
        return taskListMapper.toDto(createdTaskList);

    }
}
