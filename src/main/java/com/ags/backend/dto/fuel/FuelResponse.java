package com.ags.backend.dto.fuel;

import com.ags.backend.dto.model.ModelDto;

import java.math.BigDecimal;

public record FuelResponse (ModelDto model, BigDecimal price, String transmission, String traction, int usageTime, String nameFuel) {
}

