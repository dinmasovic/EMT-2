package com.example.demo.service.domain.impl;

import com.example.demo.model.domain.Place;
import com.example.demo.repository.PlaceRepository;
import com.example.demo.service.domain.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    private PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<Place> listAll() {
        return placeRepository.findAll();
    }

    @Override
    public Optional<Place> findById(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public Optional<Place> save(Place place) {
        if(place.getCategory()!=null && place.getHost()!=null && place.getNumRooms()>0){
            return Optional.of(placeRepository.save(new Place(place.getName(),place.getCategory(),place.getHost(),place.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Place> update(Long id, Place place) {
        return placeRepository.findById(id)
                .map(existing -> {
                    if (place.getName() != null) {
                        existing.setName(place.getName());
                    }
                    if (place.getHost() != null) {
                        existing.setHost(place.getHost());
                    }
                    if (place.getCategory() != null) {
                        existing.setCategory(place.getCategory());
                    }
                    if (place.getNumRooms()>0) {
                        existing.setNumRooms(place.getNumRooms());
                    }
                    if (place.getRented()==false)
                        existing.removeRented();
                    return placeRepository.save(existing);
                });
    }

    @Override
    public void delete(Long id) {
        placeRepository.deleteById(id);
    }

    @Override
    public void rent(Long id) {
        Place place = placeRepository.findById(id).get();
        place.setRented();
        placeRepository.save(place);
    }
}
