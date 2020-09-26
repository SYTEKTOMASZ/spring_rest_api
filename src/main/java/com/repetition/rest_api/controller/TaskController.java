package com.repetition.rest_api.controller;

import com.repetition.rest_api.mapper.TaskMapper;
import com.repetition.rest_api.model.Task;
import com.repetition.rest_api.model.dto.CreateTaskDto;
import com.repetition.rest_api.model.dto.TaskDto;
import com.repetition.rest_api.model.dto.UpdateTaskDto;
import com.repetition.rest_api.model.enums.Status;
import com.repetition.rest_api.model.enums.Type;
import com.repetition.rest_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskMapper taskMapper;
    @GetMapping("/tasks")
    public List<TaskDto> getTasks(){
        return taskMapper.toDtos(taskService.getTasks());
    }
    @GetMapping("/tasks/{taskId}")
    public TaskDto getTask(@PathVariable("taskId") long taskId){
        return taskMapper.toDto(taskService.getTask(taskId));
    }
    @PostMapping("/addTask")
    public void createTask(CreateTaskDto createTaskDto){
        taskService.createTask(taskMapper.fromDto(createTaskDto));
    }

    @GetMapping("/tasksWithFilter")
    public List<TaskDto> getTasksByTypeAndStatusAndUser(
            @RequestParam Status status,
            @RequestParam Type type,
            @RequestParam long userId){
        return taskMapper.toDtos(taskService.getTasksByTypeAndStatusAndUser(type,status,userId));
    }
    // 1. edycja zadania: title, status, type
    @PutMapping("/updateTask")
    public void updateTask(
            @RequestParam long id,
            @RequestParam String title,
            @RequestParam Status status,
            @RequestParam Type type){
        UpdateTaskDto updateTaskDto = new UpdateTaskDto(id, title,type,status);
        taskService.updateTask(taskMapper.fromDto(updateTaskDto));
    }
    // 2. zmiana właściciela zadania
    @PutMapping("/updateTaskOwner")
    public void updateTaskOwner(
            @RequestParam("taskId") long taskId,
            @RequestParam("userId") long userId){
        taskService.updateTaskOwner(taskId,userId);
    }
    // 3. usuwanie zadania po id -> ? relacja ?
    @DeleteMapping("/deleteTask")
    public void deleteTask(
            @RequestParam long id){
        taskService.deleteTask(id);
    }
    @GetMapping("/groupTasksByStatus")
    public Map<Status, List<Task>> groupTasksByStatus(){
        return taskService.groupTasksByStatus();
    }
}
