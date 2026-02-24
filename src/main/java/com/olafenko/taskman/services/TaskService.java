package com.olafenko.taskman.services;

import com.olafenko.taskman.exceptions.custom_exceptions.ResourceNotFoundException;
import com.olafenko.taskman.models.Task;
import com.olafenko.taskman.models.dtos.tasks.CreateTaskRequest;
import com.olafenko.taskman.models.dtos.tasks.EditTaskRequest;
import com.olafenko.taskman.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    //returns all Tasks
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task createTask(CreateTaskRequest createTaskRequest){

        Task task = Task.builder()
                .title(createTaskRequest.title())
                .description(createTaskRequest.description())
                .status(createTaskRequest.status())
                .priority(createTaskRequest.priority()).build();

        return taskRepository.save(task);
    }

    public Task editTask(int id, EditTaskRequest editTaskRequest){

        Task editedTask = taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found"));

        editedTask.setTitle(editTaskRequest.title());
        editedTask.setDescription(editTaskRequest.description());
        editedTask.setStatus(editTaskRequest.status());
        editedTask.setPriority(editTaskRequest.priority());
        editedTask.setEditedAt(LocalDateTime.now());

        return taskRepository.save(editedTask);
    }

    public void deleteTask(int id){

        Task task = taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);

    }

}
