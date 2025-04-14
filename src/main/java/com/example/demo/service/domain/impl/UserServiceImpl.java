package com.example.demo.service.domain.impl;

import com.example.demo.model.domain.Place;
import com.example.demo.model.domain.User;
import com.example.demo.model.enumerations.Role;
import com.example.demo.model.exception.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.domain.PlaceService;
import com.example.demo.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlaceService  placeService;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,PlaceService  placeService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.placeService = placeService;
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException("Invalid username or password");
        if (!password.equals(repeatPassword)) throw new PasswordsDoNotMatchException("The second passowrd does not match with the first one");
        if (userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);

    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException("Invalid arguments");
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
                InvalidUserCredentialsException::new);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public void rent(String username,Long placeId) {
        User user=userRepository.findByUsername(username).get();
        Place place=placeService.findById(placeId).get();
        if(place.getRented()){
            throw new AlreadyRentedException("This place is already rented");
        }
        place.setRented();
        user.addRented(place);
        userRepository.save(user);
    }

    @Override
    public void planningOn(String username, Long id) {
        User user=userRepository.findByUsername(username).get();
        Place place=placeService.findById(id).get();
        if(place.getRented()){
            throw new AlreadyRentedException("This place is already rented");
        }
        user.addPlanning(place);
        userRepository.save(user);
    }

    @Override
    public void confirmPlanning(String username) {
        User user=userRepository.findByUsername(username).get();
        user.getPlanning().forEach(Place::setRented);
        user.getPlanning().forEach(user::addRented);
        user.getPlanning().clear();
        userRepository.save(user);
    }


}
