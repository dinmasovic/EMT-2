package com.example.demo.web;

import com.example.demo.dto.create.CreatePlaceDto;
import com.example.demo.dto.display.DisplayPlaceDto;
import com.example.demo.model.domain.Place;
import com.example.demo.service.application.PlaceApplicationService;
import com.example.demo.service.domain.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/place")
@Tag(name = "Place API", description = "Endpoints for managing places")
public class PlaceController {
    private final PlaceApplicationService placeService;

    public PlaceController(PlaceApplicationService placeService) {
        this.placeService = placeService;
    }
    @GetMapping
    @Operation(summary = "Get places", description = "Find all places")
    List<DisplayPlaceDto> listAll(){
        return placeService.listAll();
    }
    @PostMapping
    @Operation(summary = "create place", description = "create a place")
    Optional<DisplayPlaceDto> createPlace(@RequestBody CreatePlaceDto place){
        return placeService.save(place);
    }
    @PutMapping("/{id}")
    @Operation(summary = "update place", description = "update a place")
    public Optional<DisplayPlaceDto> updatePlace(@PathVariable Long id, @RequestBody CreatePlaceDto place) {
        return placeService.update(id, place);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete place", description = "delete place by id")
    public void deletePlace(@PathVariable Long id) {
        placeService.delete(id);
    }
    @PutMapping("/rent/{id}")
    @Operation(summary = "Rent a place", description = "Rent a place by id")
    public void rentPlace(@PathVariable Long id) {
        placeService.rent(id);
    }
}
