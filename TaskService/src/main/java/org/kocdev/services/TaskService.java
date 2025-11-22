package org.kocdev.services;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.kocdev.entities.Task;
import org.kocdev.entities.TaskStatus;
import org.kocdev.payload.request.TaskRequest;
import org.kocdev.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task create(TaskRequest taskRequest , String userId){
        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .priority(taskRequest.getPriority())
                .dueDate(taskRequest.getDueDate())
                .assignedTo(taskRequest.getAssignedTo())
                .createdBy(UUID.fromString(userId))
                .build();

        return taskRepository.save(task);
    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public List<Task> getMyTasks(String userId){
        return taskRepository.findByAssignedTo(UUID.fromString(userId));
    }

    public Task updateStatus(UUID taskId, TaskStatus newStatus){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }
}
