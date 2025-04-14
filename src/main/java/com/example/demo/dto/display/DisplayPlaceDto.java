package com.example.demo.dto.display;

import com.example.demo.model.domain.Host;
import com.example.demo.model.domain.Place;
import com.example.demo.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayPlaceDto(String name, Category category, Host host, int numRooms) {
    public static DisplayPlaceDto from(Place place) {
        return new DisplayPlaceDto(place.getName(),place.getCategory(),place.getHost(),place.getNumRooms());
    }
    public static List<DisplayPlaceDto> from(List<Place> places) {
        return places.stream().map(DisplayPlaceDto::from).collect(Collectors.toList());
    }
}
