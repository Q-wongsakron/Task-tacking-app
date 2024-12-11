package com.example.tasks_tracking.services.impl;

import com.example.tasks_tracking.entity.Task;
import com.example.tasks_tracking.entity.TaskList;
import com.example.tasks_tracking.entity.TaskPriority;
import com.example.tasks_tracking.entity.TaskStatus;
import com.example.tasks_tracking.repositories.TaskListRepository;
import com.example.tasks_tracking.repositories.TaskRepository;
import com.example.tasks_tracking.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<Task> listTask(Long taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(Long taskListId, Task task) {
       if (null != task.getId()){
           throw new IllegalArgumentException("Task already has an ID!");
       }

       if (null == task.getTitle()) {
           throw new IllegalArgumentException("Task must have a title!");
       }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList= taskListRepository.findById(taskListId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Task List ID provided!"));

        LocalDateTime now = LocalDateTime.now();

        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now

        );
        return taskRepository.save(taskToSave);
    }
}
