package com.example.demo.service.domain;

import com.example.demo.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(Country country);
    Optional<Country> update(Long id,Country country);
    void delete(Long id);

}
