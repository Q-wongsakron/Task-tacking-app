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
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.util.TypeUtils.type;

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

    // implement get task method
    @Override
    public Optional<Task> getTask(Long taskListId, Long taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task updateTask(Long taskListId, Long taskId, Task task) {
        System.out.println(taskListId.getClass());
        System.out.println(task.getId().getClass());
        if(null == task.getId()) {
            throw new IllegalArgumentException("Task must have an ID!");
        }
        if(!Objects.equals(taskId, task.getId())){
            throw new IllegalArgumentException("Task IDs do not match");
        }
        if(null == task.getPriority()){
            throw new IllegalArgumentException("Task must have a valid priority");
        }
        if(null == task.getStatus()){
            throw new IllegalArgumentException("Task must have valid status");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(()-> new IllegalArgumentException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }
}
