package com.example.tasks_tracking.controller;

import com.example.tasks_tracking.dto.TaskDto;
import com.example.tasks_tracking.entity.Task;
import com.example.tasks_tracking.mappers.TaskMapper;
import com.example.tasks_tracking.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

        return taskService.listTask(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") Long taskListId, @RequestBody TaskDto taskDto){
        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(createdTask);
    }

    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(
            @PathVariable("task_list_id") Long taskListId,
            @PathVariable("task_id") Long taskId
    ){
        System.out.println(taskService.getTask(taskListId, taskId).map(taskMapper::toDto));
        return taskService.getTask(taskListId, taskId).map(taskMapper::toDto);
    }

    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id") Long taskListId,
                              @PathVariable("task_id") Long taskId,
                              @RequestBody TaskDto taskDto){
        Task updateTask = taskService.updateTask(
                taskListId,
                taskId,
                taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updateTask);
    }


}
