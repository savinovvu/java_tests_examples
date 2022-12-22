package com.example.testexample.controller;

import com.example.testexample.model.User;
import com.example.testexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;


    @GetMapping("/api/v1/users")
    public List<User> allUsers() {
        return userService.findAll();
    }


    @GetMapping("/api/v1/users/{id}")
    public User allUsers(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/api/checker")
    public String checker() {
        return "ok";
    }

}
