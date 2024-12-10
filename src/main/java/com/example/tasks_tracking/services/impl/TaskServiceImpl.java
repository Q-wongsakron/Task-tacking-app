package com.example.tasks_tracking.services.impl;

import com.example.tasks_tracking.entity.Task;
import com.example.tasks_tracking.repositories.TaskRepository;
import com.example.tasks_tracking.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> listTask(Long taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }
}
