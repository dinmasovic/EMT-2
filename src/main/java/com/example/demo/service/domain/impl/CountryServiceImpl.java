package com.example.demo.service.domain.impl;

import com.example.demo.model.domain.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(Country country) {
        if(country.getContinent()!=null && country.getName()!=null){
            return Optional.of(countryRepository.save(new Country(country.getName(),country.getContinent())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Country> update(Long id,Country country) {
        return countryRepository.findById(id)
                .map(existing -> {
                    if (country.getName() != null) {
                        existing.setName(country.getName());
                    }
                    if (country.getContinent() != null) {
                        existing.setContinent(country.getContinent());
                    }
                    return countryRepository.save(existing);
                });

    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
