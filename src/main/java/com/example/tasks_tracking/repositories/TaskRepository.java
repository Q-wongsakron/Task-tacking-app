package com.example.tasks_tracking.repositories;

import com.example.tasks_tracking.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    List<Task> findByTaskListId(Long taskListId);

    Optional<Task> findByTaskListIdAndId(Long taskListId, Long id);

    void deleteByTaskListIdAndId(Long taskListId, Long id);
}
