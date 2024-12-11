package com.example.tasks_tracking.services;

import com.example.tasks_tracking.entity.Task;

import java.util.List;

public interface TaskService {
    // list of tasks that belong to the Task List with id
    List<Task> listTask(Long taskListId);
    Task createTask(Long taskListId, Task task);
}
