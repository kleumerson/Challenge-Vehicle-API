package com.ags.backend.entity.brand;

import com.ags.backend.entity.model.Model;
import com.ags.backend.entity.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brand", schema = "ags")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_brand", unique = true, nullable = false)
    private int idBrand;

    @Column(name = "name_brand", length = 100)
    private String nameBrand;

    @JsonIgnore
    @OneToMany(targetEntity = Model.class, mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Model> models;
}
