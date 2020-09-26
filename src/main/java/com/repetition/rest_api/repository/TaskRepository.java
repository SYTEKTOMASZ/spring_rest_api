package com.repetition.rest_api.repository;

import com.repetition.rest_api.model.Task;
import com.repetition.rest_api.model.User;
import com.repetition.rest_api.model.enums.Status;
import com.repetition.rest_api.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    // SELECT * FROM task WHERE user = ? AND status = ? AND type = ?;
    List<Task> findAllByUserAndStatusAndType(User user, Status status, Type type);
}
