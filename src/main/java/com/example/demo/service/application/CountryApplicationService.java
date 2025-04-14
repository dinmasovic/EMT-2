package com.example.demo.service.application;

import com.example.demo.dto.create.CreateCountryDto;
import com.example.demo.dto.display.DisplayCountryDto;
import com.example.demo.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> listAll();
    Optional<DisplayCountryDto> findById(Long id);
    Optional<DisplayCountryDto> save(CreateCountryDto country);
    Optional<DisplayCountryDto> update(Long id,CreateCountryDto country);
    void delete(Long id);
}
