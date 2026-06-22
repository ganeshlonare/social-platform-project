package com.project.socialPlatform.userService.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String name, email, password;
}
