package com.ags.backend.dto.model;

import com.ags.backend.dto.brand.BrandDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelDto {
    private String modelName;
    private BrandDto brand;
}
