package org.java.restful.service.impl;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.java.restful.dto.UserDto;
import org.java.restful.entity.User;
import org.java.restful.exception.EmailAlreadyExistsException;
import org.java.restful.exception.ResourceNotFoundException;
import org.java.restful.mapper.AutoUserMapper;
import org.java.restful.mapper.UserMapper;
import org.java.restful.repository.UserRepository;
import org.java.restful.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UserDto into User JPA Entity
        //User user= UserMapper.mapToUser(userDto);
        //User user= modelMapper.map(userDto,User.class);

        Optional<User> optionalUser=userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw  new EmailAlreadyExistsException("Email Already Exists");
        }

        User user= AutoUserMapper.MAPPER.mapToUser(userDto);


        User savedUser= userRepository.save(user);

        //Convert User JPA Entity into UserDto
        //UserDto savedUserDto=UserMapper.mapToUserDto(savedUser);
        //UserDto savedUserDto=modelMapper.map(savedUser,UserDto.class);
        UserDto savedUserDto=AutoUserMapper.MAPPER.mapToUserDto(savedUser);


        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User","id",userId)
        );
        //return UserMapper.mapToUserDto(user);
        //return modelMapper.map(user,UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> allUsers() {
        List<User> users= userRepository.findAll().stream().toList();
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());
//        return users.stream().map((user -> modelMapper.map(user,UserDto.class)))
//                .collect(Collectors.toList());

        return users.stream().map((user -> AutoUserMapper.MAPPER.mapToUserDto(user)))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser=userRepository.findById(user.getId()).orElseThrow(
                ()->new ResourceNotFoundException("User","id", user.getId())
        );
        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        existingUser.setEmail(user.getEmail());
        User saveUser=userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(saveUser);
        //return modelMapper.map(saveUser,UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(saveUser);

    }

    @Override
    public void deleteUser(Long id) {
        User existingUser=userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User","id",id)
        );
        userRepository.deleteById(existingUser.getId());

    }
}
