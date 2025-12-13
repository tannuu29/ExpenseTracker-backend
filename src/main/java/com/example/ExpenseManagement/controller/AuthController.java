package com.example.ExpenseManagement.controller;

import com.example.ExpenseManagement.dto.AuthReqDto;
import com.example.ExpenseManagement.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/login")
    public String getToken(@RequestBody AuthReqDto authReqDto){
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(
                    authReqDto.getUsername(),
                    authReqDto.getPassword()
            ));
            return jwtUtils.generateToken(authReqDto.getUsername());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("user not found");
        }
    }
}
