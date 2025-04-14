package com.example.demo.dto.create;

import com.example.demo.model.domain.Host;
import com.example.demo.model.domain.Place;
import com.example.demo.model.enumerations.Category;

public record CreatePlaceDto(String name, Category category, Host host, int numRooms) {
    public Place toPlace(){
        return new Place(name,category,host,numRooms);
    }
}
