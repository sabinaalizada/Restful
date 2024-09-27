package org.java.restful.mapper;

import org.java.restful.dto.UserDto;
import org.java.restful.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto){
        User user=new User(userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail()
        );
        return user;

    }

}

