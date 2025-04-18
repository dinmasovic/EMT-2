package com.example.demo.web;

import com.example.demo.dto.create.CreateCountryDto;
import com.example.demo.dto.display.DisplayCountryDto;
import com.example.demo.model.domain.Country;
import com.example.demo.service.application.CountryApplicationService;
import com.example.demo.service.domain.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
@Tag(name = "Country API", description = "Endpoints for managing countries")
public class CountryController {
    private final CountryApplicationService countryService;
    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }
    @GetMapping
    @Operation(summary = "Get countries", description = "Find all countries")
    public List<DisplayCountryDto> getCountries() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return countryService.listAll();
    }

    @PostMapping
    @Operation(summary = "Create a country", description = "Create a country")
    public Optional<DisplayCountryDto> createCountry(@RequestBody CreateCountryDto country) {
        System.out.println(country.toCountry().getId());
        return countryService.save(country);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a country", description = "Update a country by id")
    public Optional<DisplayCountryDto> updateCountry(@PathVariable Long id, @RequestBody CreateCountryDto country) {
        return countryService.update(id, country);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a country", description = "Delete a country by id")
    public void deleteCountry(@PathVariable Long id) {
        countryService.delete(id);
    }
}
