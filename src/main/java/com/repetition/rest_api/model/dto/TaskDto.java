package com.repetition.rest_api.model.dto;

import com.repetition.rest_api.model.User;
import com.repetition.rest_api.model.enums.Status;
import com.repetition.rest_api.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskDto {
    private long id;
    private String title;
    private LocalDateTime dateAdded;
    private Type type;
    private Status status;
    private User user;
}
