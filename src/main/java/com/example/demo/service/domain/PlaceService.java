package com.example.demo.service.domain;

import com.example.demo.model.domain.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    List<Place> listAll();
    Optional<Place> findById(Long id);
    Optional<Place> save(Place country);
    Optional<Place> update(Long id,Place country);
    void delete(Long id);
    void rent(Long id);
}
