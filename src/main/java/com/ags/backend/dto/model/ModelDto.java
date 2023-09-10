package com.ags.backend.dto.model;

import com.ags.backend.dto.brand.BrandDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelDto {
    private int IdModel;
    private String modelName;
    private BrandDto brand;
}
