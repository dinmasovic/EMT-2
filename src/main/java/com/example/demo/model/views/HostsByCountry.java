package com.example.demo.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("SELECT * FROM public.hosts_by_country")
@Immutable
@Table(name = "hosts_by_country")
public class HostsByCountry {

    @Id
    @Column(name="country_id")
    private Long countryId;
    @Column(name="country_name")
    private String countryName;
    @Column(name="host_count")
    private Long hostCount;

    public HostsByCountry() {}

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getHostCount() {
        return hostCount;
    }

    public void setHostCount(Long hostCount) {
        this.hostCount = hostCount;
    }
}