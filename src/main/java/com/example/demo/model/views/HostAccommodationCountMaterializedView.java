package com.example.demo.model.views;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
@Entity
@Table(name = "host_accommodation_count")
@Subselect("SELECT * FROM public.host_accommodation_count")
@Immutable
@Data
public class HostAccommodationCountMaterializedView {

    @Id
    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "accommodation_count")
    private Integer accommodationCount;
    public Long getHostId() {
        return hostId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAccommodationCount() {
        return accommodationCount;
    }
}