package org.kocdev.repositories;

import org.kocdev.entities.Task;
import org.kocdev.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository  extends JpaRepository<Task, UUID> {
    List<Task> findByAssignedTo(UUID assignedTo);

    List<Task> findByCreatedBy(UUID createdBy);

    List<Task> findByStatus(TaskStatus status);
}
