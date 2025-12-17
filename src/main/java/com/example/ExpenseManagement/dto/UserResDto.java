package com.example.ExpenseManagement.dto;

import com.example.ExpenseManagement.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserResDto {
    private Long userId;
    private String username;
    private String name;
//    private String password;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private String role;

}
