package com.experience.user.controller;

import com.experience.user.model.Users;

import com.experience.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/getUsers")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("createUser")
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        try {
            usersService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
