package com.example.demo.repository;

import com.example.demo.model.domain.Host;
import com.example.demo.model.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {
    @Query("SELECT h FROM Host h")
    public List<HostProjection> hostProjection();
}
