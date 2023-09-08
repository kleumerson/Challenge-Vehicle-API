package com.ags.backend.dto.fuel;

import com.ags.backend.dto.vehicle.VehicleDto;

public class FuelMapper {
    public static FuelMapper fromFuelToResponse;
    public static FuelResponse fromFuelToResponse(VehicleDto vehicleDto, FuelDto fuelDto){
        return new FuelResponse(
                vehicleDto.getModel(),
                vehicleDto.getPrice(),
                vehicleDto.getTransmission(),
                vehicleDto.getTraction(),
                vehicleDto.getUsageTime(),
                fuelDto.getNameFuel()

        );
    }
}
