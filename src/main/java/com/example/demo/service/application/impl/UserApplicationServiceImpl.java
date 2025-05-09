package com.example.demo.service.application.impl;

import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.LoginUserDto;
import com.example.demo.dto.create.CreateUserDto;
import com.example.demo.dto.display.DisplayUserDto;
import com.example.demo.helper.JwtHelper;
import com.example.demo.model.domain.User;
import com.example.demo.service.application.UserApplicationService;
import com.example.demo.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginResponseDto(token));
    }


    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public void rent(String username, Long id) {
        userService.rent(username, id);
    }

    @Override
    public void planningOn(String username, Long id) {
        userService.planningOn(username,id);
    }

    @Override
    public void confirmPlanning(String username) {
        userService.confirmPlanning(username);
    }

}
