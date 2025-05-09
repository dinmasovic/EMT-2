package com.example.demo.repository;

import com.example.demo.model.views.HostsByCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HostsByCountryRepository extends JpaRepository<HostsByCountry,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW hosts_by_country", nativeQuery = true)
    void refreshMaterializedView();
}
