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
@AllArgsConstructor
@Builder
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    //przy tworzeniu obiektu ustawia czas na terazniejszy
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime editedAt;

}
