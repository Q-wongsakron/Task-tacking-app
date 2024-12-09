package com.example.tasks_tracking.controller;

import com.example.tasks_tracking.dto.TaskDto;
import com.example.tasks_tracking.mappers.TaskMapper;
import com.example.tasks_tracking.services.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// represent a rest controller
@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTask(@PathVariable("task_list_id") Long taskListId){

        System.out.println(taskService.listTask(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList());
        return taskService.listTask(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
}
