package com.olafenko.taskman.models.dtos;

import com.olafenko.taskman.models.enums.TaskPriority;
import com.olafenko.taskman.models.enums.TaskStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EditTaskRequest(

        @NotBlank(message = "Title cannot be empty.")
        @Size(max = 50, message = "Title max length is 50 chars.")
        @NotNull(message = "Title is required.")
        String title,

        @Size(max = 200, message = "Description max length is 200 chars.")
        @Nullable
        String description,

        @NotNull(message = "Status is required.")
        TaskStatus status,

        @NotNull(message = "Priority is required.")
        TaskPriority priority) {
}
