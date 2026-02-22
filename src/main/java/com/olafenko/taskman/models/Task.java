package com.olafenko.taskman.models;

import com.olafenko.taskman.models.enums.TaskPriority;
import com.olafenko.taskman.models.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @Enumerated(value = EnumType.STRING)
    private TaskPriority priority;

    //przy tworzeniu obiektu ustawia czas na terazniejszy
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime editedAt;


    public Task(String title,String description,TaskStatus status, TaskPriority priority){
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

}
