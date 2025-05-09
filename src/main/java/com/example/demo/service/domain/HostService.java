package com.example.demo.service.domain;

import com.example.demo.model.domain.Host;
import com.example.demo.model.projections.HostProjection;
import com.example.demo.model.views.HostsByCountry;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> listAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(Host country);
    Optional<Host> update(Long id,Host country);
    void delete(Long id);
    public void refreshMaterializedView();
    public List<HostsByCountry> byCountries();
    public List<HostProjection> projection();
}
