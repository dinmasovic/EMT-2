package com.example.demo.dto.create;

import com.example.demo.model.domain.Country;
import com.example.demo.model.domain.Host;

public record CreateHostDto(String name, String surname, Country country){
    public Host toHost(){
        return new Host(name, surname, country);
    }
}
