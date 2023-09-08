package com.ags.backend.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandDto {
    public BrandDto(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    private String nameBrand;
}
