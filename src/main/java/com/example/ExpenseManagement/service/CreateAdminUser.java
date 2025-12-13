package com.example.ExpenseManagement.service;

import com.example.ExpenseManagement.config.SecurityConfig;
import com.example.ExpenseManagement.entity.User;
import com.example.ExpenseManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CreateAdminUser {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SecurityConfig config;

    @Bean
    public CommandLineRunner createAdminTest(){
        return args -> {
            if(userRepo.findByUsername("tannu").isEmpty()){
                User user = new User();
                user.setUsername("tannu");
                user.setPassword(config.encoder().encode("adminnn"));
                user.setName("tannu");
                userRepo.save(user);
            }
        };
    }
}
