package com.olafenko.taskman.servicies;

import com.olafenko.taskman.models.Task;
import com.olafenko.taskman.models.dtos.CreateTaskRequest;
import com.olafenko.taskman.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    //funkcja zwracajaca wszystkie zadania
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task createTask(CreateTaskRequest createTaskRequest){

        Task task = new Task(
                createTaskRequest.title(),
                createTaskRequest.description(),
                createTaskRequest.status(),
                createTaskRequest.priority());

        return taskRepository.save(task);
    }

    public Task editTask(int id,CreateTaskRequest editTaskRequest){

        Task editedTask = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));

        editedTask.setTitle(editTaskRequest.title());
        editedTask.setDescription(editTaskRequest.description());
        editedTask.setStatus(editTaskRequest.status());
        editedTask.setPriority(editTaskRequest.priority());
        editedTask.setEditedAt(LocalDateTime.now());

        return taskRepository.save(editedTask);
    }

    public void deleteTask(int id){

        Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));

        taskRepository.delete(task);

    }

}
