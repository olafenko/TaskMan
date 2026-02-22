package com.olafenko.taskman.models.dtos;

import com.olafenko.taskman.models.enums.TaskPriority;
import com.olafenko.taskman.models.enums.TaskStatus;

public record EditTaskRequest(String title, String description, TaskStatus status, TaskPriority priority) {
}
