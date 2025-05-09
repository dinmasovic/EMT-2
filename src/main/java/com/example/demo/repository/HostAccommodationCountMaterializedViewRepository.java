package com.example.demo.repository;

import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.views.HostAccommodationCountMaterializedView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HostAccommodationCountMaterializedViewRepository  extends JpaRepository<HostAccommodationCountMaterializedView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW host_accommodation_count", nativeQuery = true)
    void refreshMaterializedView();
}
