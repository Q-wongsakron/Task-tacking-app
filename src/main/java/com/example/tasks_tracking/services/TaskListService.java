package com.example.tasks_tracking.services;

import com.example.tasks_tracking.entity.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
}
