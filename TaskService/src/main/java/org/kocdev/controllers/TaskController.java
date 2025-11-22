package org.kocdev.controllers;

import lombok.RequiredArgsConstructor;
import org.kocdev.entities.Task;
import org.kocdev.entities.TaskStatus;
import org.kocdev.payload.request.TaskRequest;
import org.kocdev.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody TaskRequest taskRequest,
            @RequestHeader("X-User-Id") String userId){
        return ResponseEntity.ok(taskService.create(taskRequest, userId));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return  ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping("/my-tasks")
    public ResponseEntity<List<Task>> getMyTasks(@RequestHeader("X-User-Id") String userId){
        return ResponseEntity.ok(taskService.getMyTasks(userId));
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateStatus(
            @PathVariable UUID taskId,
            @RequestParam TaskStatus newStatus
    ){
        return ResponseEntity.ok(taskService.updateStatus(taskId, newStatus));
    }
}
