package com.olafenko.taskman.models.dtos;

import com.olafenko.taskman.models.enums.TaskPriority;
import com.olafenko.taskman.models.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(@NotBlank String title, String description, @NotBlank TaskStatus status, @NotBlank TaskPriority priority) {
}
