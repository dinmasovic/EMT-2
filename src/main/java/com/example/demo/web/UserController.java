package com.example.demo.web;

import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.LoginUserDto;
import com.example.demo.dto.create.CreateUserDto;
import com.example.demo.dto.display.DisplayUserDto;
import com.example.demo.model.exception.InvalidArgumentsException;
import com.example.demo.model.exception.InvalidUserCredentialsException;
import com.example.demo.model.exception.PasswordsDoNotMatchException;
import com.example.demo.service.application.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {

    private final UserApplicationService userApplicationService;


    private BCryptPasswordEncoder passwordEncoder;
    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
        passwordEncoder=new BCryptPasswordEncoder(10);

    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        System.out.println(loginUserDto.username());
        System.out.println(loginUserDto.password());
        try {
            LoginResponseDto displayUserDto = userApplicationService.login(
                    new LoginUserDto(loginUserDto.username(), loginUserDto.password())
            ).orElseThrow(InvalidUserCredentialsException::new);

            return ResponseEntity.ok(displayUserDto);
        } catch (InvalidUserCredentialsException e) {
            System.out.println();
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    @PostMapping("/rent")
    public void rent(@RequestParam Long placeId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        userApplicationService.rent(username, placeId);
    }


    @PostMapping("/planToRent")
    public void planToRent(@RequestParam Long placeId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userApplicationService.planningOn(username, placeId);

    }

    @PostMapping("/confirmed")
    public void confirmed() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userApplicationService.confirmPlanning(username);

    }

}
