package com.repetition.rest_api.mapper;

import com.repetition.rest_api.model.Task;
import com.repetition.rest_api.model.User;
import com.repetition.rest_api.model.dto.CreateTaskDto;
import com.repetition.rest_api.model.dto.TaskDto;
import com.repetition.rest_api.model.dto.UpdateTaskDto;
import com.repetition.rest_api.model.dto.UpdateUserDto;
import com.repetition.rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    @Autowired
    private UserRepository userRepository;

    public TaskDto toDto(Task task){
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDateAdded(),
                task.getType(),
                task.getStatus(),
                task.getUser());
    }
    public List<TaskDto> toDtos(List<Task> tasks){
        return tasks.stream().map(task -> toDto(task))
                .collect(Collectors.toList());
    }
    public Task fromDto(CreateTaskDto createTaskDto){
        Task task = new Task();
        task.setTitle(createTaskDto.getTitle());
        task.setType(createTaskDto.getType());
        task.setStatus(createTaskDto.getStatus());
        Optional<User> isUser = userRepository.findById(createTaskDto.getUserId());
        isUser.ifPresent(task::setUser);
        return task;
    }
    public Task fromDto(UpdateTaskDto updateTaskDto){
        Task task = new Task();
        task.setId(updateTaskDto.getId());
        task.setTitle(updateTaskDto.getTitle());
        task.setType(updateTaskDto.getType());
        task.setStatus(updateTaskDto.getStatus());
        return task;
    }
}
