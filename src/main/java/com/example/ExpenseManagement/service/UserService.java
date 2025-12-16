package com.example.ExpenseManagement.service;

import com.example.ExpenseManagement.config.SecurityConfig;
import com.example.ExpenseManagement.dto.ChangePasswordDto;
import com.example.ExpenseManagement.dto.UpdateProfileDto;
import com.example.ExpenseManagement.dto.UserReqDto;
import com.example.ExpenseManagement.entity.User;
import com.example.ExpenseManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SecurityConfig config;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserReqDto userReqDto){
        User user = new User();
        user.setName(userReqDto.getName());
        user.setUsername(userReqDto.getUsername());
        user.setPassword(config.encoder().encode(userReqDto.getPassword()));
        user.setMobile(userReqDto.getMobile());
        userRepo.save(user);
        return "User Successfully added";
    }

    public void changePassword(String username, ChangePasswordDto passwordDto){
        User user = userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));

        if(!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())){
            throw new RuntimeException("Password is incorrect ");
        }
        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userRepo.save(user);
    }

    public void updateProfile(String username, UpdateProfileDto profileDto){
        User user = userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        user.setName(profileDto.getName());
        user.setUsername(profileDto.getUsername());
        user.setMobile(profileDto.getMobile());

        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElse(null);
    }

//    public UserResDto getUserById(){}
}
