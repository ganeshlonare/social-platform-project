package com.project.socialPlatform.userService.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email, password;
}
