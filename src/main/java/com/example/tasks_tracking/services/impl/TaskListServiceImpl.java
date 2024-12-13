package com.example.tasks_tracking.services.impl;

import com.example.tasks_tracking.entity.TaskList;
import com.example.tasks_tracking.repositories.TaskListRepository;
import com.example.tasks_tracking.services.TaskListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    public Optional<TaskList> getTaskList(Long taskListId) {
        return taskListRepository.findById(taskListId);
    }

    // for prevent error when update and it call jpa more than 1
    @Transactional
    @Override
    public TaskList updateTaskList(Long taskListId, TaskList taskList) {
        if(null == taskList.getId()) {
            throw new IllegalArgumentException("Task list must have an ID");
        }

        if(!Objects.equals(taskList.getId(), taskListId)) {
            throw new IllegalArgumentException("Attempting to change task list ID, this is not permitted");
        }

        // check task list is exist
       TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(()-> new IllegalArgumentException("Task list not found!"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());

        return  taskListRepository.save(existingTaskList);


    }

    @Override
    public void deleteTaskList(Long taskListId) {
        // not check task list is exist because spring jpa will handle non-existing entities
        taskListRepository.deleteById(taskListId);
    }
}
