package com.example.demo.service.application;

import com.example.demo.dto.create.CreatePlaceDto;
import com.example.demo.dto.display.DisplayPlaceDto;
import com.example.demo.model.domain.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceApplicationService {
    List<DisplayPlaceDto> listAll();
    Optional<DisplayPlaceDto> findById(Long id);
    Optional<DisplayPlaceDto> save(CreatePlaceDto place);
    Optional<DisplayPlaceDto> update(Long id,CreatePlaceDto place);
    void delete(Long id);
    void rent(Long id);
}
