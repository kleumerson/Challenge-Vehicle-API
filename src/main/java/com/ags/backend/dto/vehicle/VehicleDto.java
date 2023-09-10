package com.ags.backend.dto.vehicle;

import com.ags.backend.dto.fuel.FuelDto;
import com.ags.backend.dto.model.ModelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private int idVehicle;
    private ModelDto model;
    private BigDecimal price;
    private BigDecimal finalPrice;
    private String transmission;
    private String traction;
    private int usageTime;
    private FuelDto idFuel;
}
