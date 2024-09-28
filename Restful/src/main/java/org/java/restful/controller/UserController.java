package org.java.restful.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.java.restful.dto.UserDto;
import org.java.restful.entity.User;
import org.java.restful.exception.ErrorDetails;
import org.java.restful.exception.ResourceNotFoundException;
import org.java.restful.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name="CRUD REST APIs for User Resource",
        description = "Create,Update,Get,Get All,Delete"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )

    //build create user REST API
    @PostMapping("createuser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser= userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //build get usr by id  Rest API

    @Operation(
            summary = "Get User by id REST API",
            description = "Get User by id REST API is used to get a single user from a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user=userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get all user from a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )

    @GetMapping("allusers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users=userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user in a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updatedUser= userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user in a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
//
//        ErrorDetails errorDetails=new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//
//    }

}
