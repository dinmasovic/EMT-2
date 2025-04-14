package com.example.demo.service.application;

import com.example.demo.dto.create.CreateHostDto;
import com.example.demo.dto.display.DisplayHostDto;
import com.example.demo.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> listAll();
    Optional<DisplayHostDto> findById(Long id);
    Optional<DisplayHostDto> save(CreateHostDto host);
    Optional<DisplayHostDto> update(Long id,CreateHostDto host);
    void delete(Long id);
}
