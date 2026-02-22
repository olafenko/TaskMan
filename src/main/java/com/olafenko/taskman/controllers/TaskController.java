package com.olafenko.taskman.controllers;

import com.olafenko.taskman.models.Task;
import com.olafenko.taskman.models.dtos.CreateTaskRequest;
import com.olafenko.taskman.servicies.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest createTaskRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(createTaskRequest));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Task> editTask(@PathVariable("id") Integer id,@RequestBody CreateTaskRequest editTaskRequest){
        return ResponseEntity.ok(taskService.editTask(id,editTaskRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Integer id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }


}
