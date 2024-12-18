package com.example.tasks_tracking.services;

import com.example.tasks_tracking.entity.TaskList;

import java.util.List;
import java.util.Optional;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
    // if exits will return TaskList else return Optional null
    Optional<TaskList> getTaskList(Long taskListId);

    TaskList updateTaskList(Long taskListId, TaskList taskList);

    void deleteTaskList(Long taskListId);
}
