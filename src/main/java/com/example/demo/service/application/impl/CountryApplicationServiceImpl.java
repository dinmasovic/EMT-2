package com.example.demo.service.application.impl;

import com.example.demo.dto.create.CreateCountryDto;
import com.example.demo.dto.display.DisplayCountryDto;
import com.example.demo.service.application.CountryApplicationService;
import com.example.demo.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> listAll() {
        return DisplayCountryDto.from(countryService.listAll());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto country) {
        return countryService.save(country.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto country) {
        return countryService.update(id, country.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public void delete(Long id) {
        countryService.delete(id);
    }
}
