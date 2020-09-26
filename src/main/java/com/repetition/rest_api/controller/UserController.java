package com.repetition.rest_api.controller;

import com.repetition.rest_api.mapper.UserMapper;
import com.repetition.rest_api.model.User;
import com.repetition.rest_api.model.dto.CreateUserDto;
import com.repetition.rest_api.model.dto.UpdateUserDto;
import com.repetition.rest_api.model.dto.UserDto;
import com.repetition.rest_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    // pobieranie danych z tabelki user (obiekt User)
    // mapowanie ich do klasy UserDto
    // i zwracanie do widoku
    @GetMapping("/users")
    public List<UserDto> getUsers(){
        List<User> users = userService.getUsers();
        return userMapper.toDtos(users);
    }
    @GetMapping("/users/{userId}")
    public UserDto getUser(@PathVariable("userId") long userId){
        User user = userService.getUser(userId);
        return userMapper.toDto(user);
    }
    @PostMapping("/addUser")
    public void createUser(CreateUserDto createUserDto){
        User user = userMapper.fromDto(createUserDto);
        userService.createUser(user);
    }
    @PutMapping("/updateUser")
    public void updateUser(UpdateUserDto updateUserDto){
        User userToUpdate = userService.getUser(updateUserDto.getId());
        if(userToUpdate != null){
            userToUpdate.setName(updateUserDto.getName());
            userService.updateUser(userToUpdate);
        }
    }
    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") long userId){
        userService.deleteUser(userId);
    }
}
