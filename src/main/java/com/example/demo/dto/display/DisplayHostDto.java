package com.example.demo.dto.display;

import com.example.demo.model.domain.Country;
import com.example.demo.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto(String name, String surname, Country country) {
    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto( host.getName(), host.getSurname(), host.getCountry());
    }
    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }
}
