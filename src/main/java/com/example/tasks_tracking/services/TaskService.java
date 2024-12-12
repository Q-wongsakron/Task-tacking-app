package com.example.tasks_tracking.services;

import com.example.tasks_tracking.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    // list of tasks that belong to the Task List with id
    List<Task> listTask(Long taskListId);
    Task createTask(Long taskListId, Task task);
    // request the task might not exist
    Optional<Task> getTask(Long taskListId, Long taskId);

    Task updateTask(Long taskListId, Long taskId , Task task );
}
