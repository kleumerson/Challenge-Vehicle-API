package com.ags.backend.entity.model;

import com.ags.backend.entity.brand.Brand;
import com.ags.backend.entity.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "model", schema = "ags")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model", unique = true, nullable = false)
    private int idModel;

    @Column(name = "name_model", length = 100)
    private String nameModel;

    @JsonIgnore
    @ManyToOne(targetEntity = Brand.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "id_brand")
    private Brand brand;

    @JsonIgnore
    @OneToMany(targetEntity = Vehicle.class, mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;
}
