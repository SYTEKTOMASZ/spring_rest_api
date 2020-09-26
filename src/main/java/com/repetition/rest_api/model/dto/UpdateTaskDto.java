package com.repetition.rest_api.model.dto;

import com.repetition.rest_api.model.enums.Status;
import com.repetition.rest_api.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTaskDto {
    private long id;
    private String title;
    private Type type;
    private Status status;
}
