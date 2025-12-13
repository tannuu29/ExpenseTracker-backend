package com.example.ExpenseManagement.dto;

import lombok.Data;

@Data
public class UserResDto {
    private Long userId;
    private String username;
    private String name;
    private String password;
    private String mobile;
}
