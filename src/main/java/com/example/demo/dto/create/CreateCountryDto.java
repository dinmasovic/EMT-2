package com.example.demo.dto.create;

import com.example.demo.model.domain.Country;

public record CreateCountryDto(String name, String continent) {
    public Country toCountry(){
        return new Country(name,continent);
    }
}
