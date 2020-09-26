package com.repetition.rest_api.model;

import com.repetition.rest_api.model.enums.Status;
import com.repetition.rest_api.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private LocalDateTime dateAdded = LocalDateTime.now();
    @Enumerated
    private Type type;
    @Enumerated
    private Status status;
    // task to user ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
