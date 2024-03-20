package com.app.crudswaggerapi.controller;

import com.app.crudswaggerapi.model.User;
import com.app.crudswaggerapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> createUser(){
        return ResponseEntity.ok(userService.findUsers());
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> createUser(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }
}
