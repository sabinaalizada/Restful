package org.java.restful.service;

import org.java.restful.dto.UserDto;
import org.java.restful.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> allUsers();
    UserDto updateUser(UserDto user);
    void deleteUser(Long id);
}
