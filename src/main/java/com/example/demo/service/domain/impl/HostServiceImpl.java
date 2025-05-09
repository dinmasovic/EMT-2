package com.example.demo.service.domain.impl;

import com.example.demo.events.HostCreatedEvent;
import com.example.demo.model.domain.Host;
import com.example.demo.model.projections.HostProjection;
import com.example.demo.model.views.HostsByCountry;
import com.example.demo.repository.HostAccommodationCountMaterializedViewRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.repository.HostsByCountryRepository;
import com.example.demo.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final HostAccommodationCountMaterializedViewRepository materializedViewRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final HostsByCountryRepository hostsByCountryRepository;

    public HostServiceImpl(HostRepository hostRepository, HostAccommodationCountMaterializedViewRepository materializedViewRepository, ApplicationEventPublisher eventPublisher, HostsByCountryRepository hostsByCountryRepository) {
        this.hostRepository = hostRepository;
        this.materializedViewRepository = materializedViewRepository;
        this.eventPublisher = eventPublisher;
        this.hostsByCountryRepository = hostsByCountryRepository;
    }

    @Override
    public List<Host> listAll() {
        return hostRepository.findAll();
    }

    public List<HostsByCountry> byCountries(){
        return hostsByCountryRepository.findAll();
    }

    @Override
    public List<HostProjection> projection() {
       return hostRepository.hostProjection();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(Host host) {
        if(host.getName()!=null && host.getSurname()!=null && host.getCountry()!=null){
            Host saved = hostRepository.save(new Host(host.getName(), host.getSurname(), host.getCountry()));
            eventPublisher.publishEvent(new HostCreatedEvent(saved));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id)
                .map(existing -> {
                    if (host.getName() != null) {
                        existing.setName(host.getName());
                    }
                    if (host.getSurname() != null) {
                        existing.setSurname(host.getSurname());
                    }
                    if (host.getCountry() != null) {
                        existing.setCountry(host.getCountry());
                    }
                    return hostRepository.save(existing);
                });
    }

    @Override
    public void delete(Long id) {
        hostRepository.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
        materializedViewRepository.refreshMaterializedView();
    }

}
