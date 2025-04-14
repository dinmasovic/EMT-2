package com.example.demo.service.application;

import com.example.demo.dto.LoginUserDto;
import com.example.demo.dto.create.CreateUserDto;
import com.example.demo.dto.display.DisplayUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
    void rent(String username,Long id);
    void planningOn(String username,Long id);
    void confirmPlanning(String username);

}
