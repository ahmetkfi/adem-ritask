package org.kocdev.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.kocdev.entities.TaskPriority;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TaskRequest {
    @NotBlank
    private String title;

    private String description;

    private TaskPriority priority;

    private LocalDateTime dueDate;

    private UUID assignedTo;
}
