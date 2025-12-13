package com.example.ExpenseManagement.controller;

import com.example.ExpenseManagement.dto.UserReqDto;
import com.example.ExpenseManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserReqDto userReqDto){
        return ResponseEntity.ok(userService.addUser(userReqDto));
    }
}
