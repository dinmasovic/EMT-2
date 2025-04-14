package com.example.demo.service.domain;

import com.example.demo.model.domain.User;
import com.example.demo.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);

    UserDetails loadUserByUsername(String username);

    void rent(String username,Long id);

}
