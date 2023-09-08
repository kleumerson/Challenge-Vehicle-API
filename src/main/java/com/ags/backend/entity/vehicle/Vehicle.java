package com.ags.backend.entity.vehicle;

import com.ags.backend.entity.fuel.Fuel;
import com.ags.backend.entity.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle", schema = "ags")
public class Vehicle  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle", unique = true, nullable = false)
    private int idVehicle;

    @Column(name = "trans_vehicle", length = 3)
    private String transmission;

    @Column(name = "tract_vehicle", length = 3)
    private String traction;

    @Column(name = "price_vehicle")
    private float price;

    @Column(name = "usage_vehicle", length = 3)
    private int usageTime;

    @JsonIgnore
    @ManyToOne(targetEntity = Model.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id", referencedColumnName = "id_model")
    private Model model;

    @JsonIgnore
    @ManyToOne(targetEntity = Fuel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fuel_id", referencedColumnName = "id_fuel")
    private Fuel fuel;
}
