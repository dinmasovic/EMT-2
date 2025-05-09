package com.example.demo.listeners;

import com.example.demo.events.HostCreatedEvent;
import com.example.demo.repository.HostsByCountryRepository;
import com.example.demo.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandler {
    private final HostsByCountryRepository repo;

    public HostEventHandler( HostsByCountryRepository repo) {
        this.repo = repo;
    }


    @EventListener
    public void onProductCreated(HostCreatedEvent event) {
        System.out.println("za drzavi");
        this.repo.refreshMaterializedView();
    }

}
