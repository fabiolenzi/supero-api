package me.lenzi.superoapi.controller;

import me.lenzi.superoapi.exception.TaskNotFoundException;
import me.lenzi.superoapi.model.Task;
import me.lenzi.superoapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    // Get All Notes
    @GetMapping("/tasks")
    public List<Task> getAllNotes() {
        return taskRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/tasks")
    public Task createNote(@Valid @RequestBody Task task) {

        task.setCreatedOn(new Date());
        return taskRepository.save(task);
    }

    // Get a Single Note
    @GetMapping("/tasks/{id}")
    public Task getNoteById(@PathVariable(value = "id") Long taskId) throws TaskNotFoundException {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    // Update a Note
    @PutMapping("/tasks/{id}")
    public Task updateNote(@PathVariable(value = "id") Long taskId,
                           @Valid @RequestBody Task taskDetails) throws TaskNotFoundException {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setIsCompleted(taskDetails.getIsCompleted());
        task.setLastEditedOn(new Date());

        if (taskDetails.getIsCompleted()) {
            task.setCompletedOn(new Date());
        } else {
            task.setCompletedOn(null);
        }

        Task updatedTask = taskRepository.save(task);

        return updatedTask;
    }

    // Delete a Note
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "id") Long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        taskRepository.delete(task);

        return ResponseEntity.ok().build();
    }
}