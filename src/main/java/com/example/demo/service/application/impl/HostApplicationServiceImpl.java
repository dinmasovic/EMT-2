package com.example.demo.service.application.impl;

import com.example.demo.dto.create.CreateHostDto;
import com.example.demo.dto.display.DisplayHostDto;
import com.example.demo.model.domain.Host;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.application.HostApplicationService;
import com.example.demo.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;

    public HostApplicationServiceImpl(HostService hostService) {
        this.hostService = hostService;
    }


    @Override
    public List<DisplayHostDto> listAll() {
        return DisplayHostDto.from(hostService.listAll());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        return hostService.save(host.toHost()).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto host) {
        return hostService.update(id, host.toHost()).map(DisplayHostDto::from);
    }

    @Override
    public void delete(Long id) {
        hostService.delete(id);
    }
}
