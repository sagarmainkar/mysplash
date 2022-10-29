package com.mysplash.users.controller;


import com.mysplash.users.dto.SplashUser;
import com.mysplash.users.dto.UserRegistrationRequest;
import com.mysplash.users.exceptions.UserNotFoundException;
import com.mysplash.users.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<SplashUser> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest){

        log.info("Starting user registration with email {}",userRegistrationRequest.email());
        SplashUser user =userService.registerUser(userRegistrationRequest);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SplashUser> getUser(@PathVariable("userId") Integer userId){
        log.info("Fetching details for userId: {}",userId);
        SplashUser user = userService.getUser(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);


    }

    @GetMapping
    public ResponseEntity<List<SplashUser>> getUsers(){
        log.info("Fetching all users");
        List<SplashUser> users = userService.getUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
