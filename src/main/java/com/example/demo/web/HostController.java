package com.example.demo.web;

import com.example.demo.dto.create.CreateHostDto;
import com.example.demo.dto.display.DisplayHostDto;
import com.example.demo.model.domain.Host;
import com.example.demo.model.projections.HostProjection;
import com.example.demo.service.application.HostApplicationService;
import com.example.demo.service.domain.HostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/host")
@Tag(name = "Host API", description = "Endpoints for managing hosts")
public class HostController {
    private final HostApplicationService hostService;

    public HostController(HostApplicationService hostService) {
        this.hostService = hostService;
    }
    @GetMapping("/hostNames")
    public List<HostProjection> projections(){
        return hostService.projections();
    }
    @GetMapping
    @Operation(summary = "Get hosts", description = "Find all hosts")
    public List<DisplayHostDto> getHosts() {
        return hostService.listAll();
    }
    @PostMapping
    @Operation(summary = "Create a host", description = "Create a host")
    public Optional<DisplayHostDto> createHost(@RequestBody CreateHostDto host) {
        return hostService.save(host);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update host", description = "Update host by id")
    public Optional<DisplayHostDto> updateHost(@PathVariable Long id, @RequestBody CreateHostDto host) {
        return hostService.update(id, host);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete host", description = "Delete host by id")
    public void deleteHost(@PathVariable Long id) {
        hostService.delete(id);
    }
}
