package com.example.demo.events;

import com.example.demo.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class HostCreatedEvent extends ApplicationEvent {
    private LocalDateTime when;
    public HostCreatedEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public HostCreatedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }

    public LocalDateTime getWhen() {
        return when;
    }
}
