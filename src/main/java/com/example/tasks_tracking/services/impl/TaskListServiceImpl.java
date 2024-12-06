package com.example.tasks_tracking.services.impl;

import com.example.tasks_tracking.entity.TaskList;
import com.example.tasks_tracking.repositories.TaskListRepository;
import com.example.tasks_tracking.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        // if has id it will update
        if (null != taskList.getId()){
            throw new IllegalArgumentException("Task list already has an ID!");
        }
        if( null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list title must be present!");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now

        ));
    }

    @Override
    public Optional<TaskList> getTaskList(Long id) {
        return taskListRepository.findById(id);
    }
}
