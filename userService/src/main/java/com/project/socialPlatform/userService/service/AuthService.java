package com.project.socialPlatform.userService.service;

import com.project.socialPlatform.userService.dto.LoginRequestDto;
import com.project.socialPlatform.userService.dto.SignupRequestDto;
import com.project.socialPlatform.userService.dto.UserDto;
import com.project.socialPlatform.userService.entity.User;
import com.project.socialPlatform.userService.exception.BadRequestException;
import com.project.socialPlatform.userService.repository.UserRepository;
import com.project.socialPlatform.userService.utils.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signup(SignupRequestDto signupRequestDto) {

        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());
        if(exists) throw new BadRequestException("User Already Exists");

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(BCrypt.hash(signupRequestDto.getPassword()));

        user= userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()-> new BadRequestException("Incorrect Email or Password"));

        boolean isPasswordMatch = BCrypt.match(loginRequestDto.getPassword(), user.getPassword());
        if(!isPasswordMatch) throw new BadRequestException("Incorrect Email or Password");

        return jwtService.generateJwtToken(user);
    }
}
