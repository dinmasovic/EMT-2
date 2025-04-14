package com.example.demo.service.application.impl;

import com.example.demo.dto.create.CreatePlaceDto;
import com.example.demo.dto.display.DisplayPlaceDto;
import com.example.demo.model.domain.Place;
import com.example.demo.service.application.PlaceApplicationService;
import com.example.demo.service.domain.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceApplicationServiceImpl implements PlaceApplicationService {
    private final PlaceService placeService;

    public PlaceApplicationServiceImpl(PlaceService placeService) {
        this.placeService = placeService;
    }

    @Override
    public List<DisplayPlaceDto> listAll() {
        return DisplayPlaceDto.from(placeService.listAll());
    }

    @Override
    public Optional<DisplayPlaceDto> findById(Long id) {
        return placeService.findById(id).map(DisplayPlaceDto::from);
    }

    @Override
    public Optional<DisplayPlaceDto> save(CreatePlaceDto place) {
        return placeService.save(place.toPlace()).map(DisplayPlaceDto::from);
    }

    @Override
    public Optional<DisplayPlaceDto> update(Long id, CreatePlaceDto place) {
        return placeService.update(id, place.toPlace()).map(DisplayPlaceDto::from);
    }

    @Override
    public void delete(Long id) {
        placeService.delete(id);
    }

    @Override
    public void rent(Long id) {
        Place place = placeService.findById(id).get();
        place.setRented();
        placeService.update(place.getId(),place);
    }
}
