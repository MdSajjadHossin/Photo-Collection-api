package com.springboot.photocollectionapi.controller;

import com.springboot.photocollectionapi.dto.UserDto;
import com.springboot.photocollectionapi.exception.ApiResponse;
import com.springboot.photocollectionapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    //Create User
    @PostMapping("/create/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createUser = userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);
    }

    //Update User
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto updateUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
    }

    // Delete User
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

    // get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        UserDto singleUser = userService.getUserById(userId);
        return new  ResponseEntity<UserDto>(singleUser, HttpStatus.OK);
    }

    // get All user
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> allUsers = userService.getAllUser();
        return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
    }
}
