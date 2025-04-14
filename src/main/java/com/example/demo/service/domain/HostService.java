package com.example.demo.service.domain;

import com.example.demo.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> listAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(Host country);
    Optional<Host> update(Long id,Host country);
    void delete(Long id);
}
