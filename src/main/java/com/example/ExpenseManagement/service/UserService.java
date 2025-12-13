package com.example.ExpenseManagement.service;

import com.example.ExpenseManagement.config.SecurityConfig;
import com.example.ExpenseManagement.dto.UserReqDto;
import com.example.ExpenseManagement.dto.UserResDto;
import com.example.ExpenseManagement.entity.User;
import com.example.ExpenseManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SecurityConfig config;

    public String addUser(UserReqDto userReqDto){
        User user = new User();
        user.setName(userReqDto.getName());
        user.setUsername(userReqDto.getUsername());
        user.setPassword(config.encoder().encode(userReqDto.getPassword()));
        user.setMobile(userReqDto.getMobile());
        userRepo.save(user);
        return "User Successfully added";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElse(null);
    }

//    public UserResDto getUserById(){}
}
