package com.repetition.rest_api.mapper;

import com.repetition.rest_api.model.User;
import com.repetition.rest_api.model.dto.CreateUserDto;
import com.repetition.rest_api.model.dto.UpdateUserDto;
import com.repetition.rest_api.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    // mapowanie encji na obiekt DTO
    public UserDto toDto(User user){
        return new UserDto(user.getId(), user.getName());
    }
    // mapowanie listy obiektów klasy User na listę obiektów DTO
   public List<UserDto> toDtos(List<User> users){
        return users.stream().map(user -> toDto(user))
                .collect(Collectors.toList());
   }
   // mapowanie obiektu CreateUserDto na obiekt User
   public User fromDto(CreateUserDto createUserDto){    // przy tworzeniu podajemy name
        User user = new User();
        user.setName(createUserDto.getName());
        return user;
   }
   public User fromDto(UpdateUserDto updateUserDto){    // przy edycji podajemy id i name
        return new User(updateUserDto.getId(),updateUserDto.getName());
   }
}
