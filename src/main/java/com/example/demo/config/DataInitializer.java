package com.example.demo.config;

import com.example.demo.model.domain.Country;
import com.example.demo.model.domain.Host;
import com.example.demo.model.domain.Place;
import com.example.demo.model.domain.User;
import com.example.demo.model.enumerations.Category;
import com.example.demo.model.enumerations.Role;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.repository.PlaceRepository;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository,PlaceRepository placeRepository,UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.placeRepository = placeRepository;
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    void init() {
        Country usa=new Country("United States", "North America");
        Country france=new Country("France", "Europe");
        countryRepository.save(usa);
        countryRepository.save(france);
        Host james=new Host("James","Smith",usa);
        hostRepository.save(james);
        Place apartment=new Place("Corfe Apartment", Category.APARTMENT,james,3);
        Place hotel=new Place("Double Tree", Category.HOTEL,james,50);
        placeRepository.save(apartment);
        placeRepository.save(hotel);
        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "Din",
                "Masovic",
                Role.USER
        ));
        userRepository.save(new User(
                "host",
                passwordEncoder.encode("host"),
                "Din",
                "Masovic",
                Role.HOST
        ));

    }
}
