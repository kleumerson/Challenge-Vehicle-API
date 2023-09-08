package com.ags.backend.entity.fuel;

import com.ags.backend.entity.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name ="fuel", schema = "ags")
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fuel", unique = true, nullable = false)
    private int id;

    @Column(name = "name_fuel", length = 50)
    private String nameFuel;

    @JsonIgnore
    @OneToMany(targetEntity = Vehicle.class, mappedBy = "fuel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;
}
