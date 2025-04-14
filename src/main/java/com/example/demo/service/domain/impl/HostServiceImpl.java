package com.example.demo.service.domain.impl;

import com.example.demo.model.domain.Host;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Host> listAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(Host host) {
        if(host.getName()!=null && host.getSurname()!=null && host.getCountry()!=null){
            return Optional.of(hostRepository.save(new Host(host.getName(),host.getSurname(),host.getCountry())));
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
}
