package com.ags.backend.repository.brand;

import com.ags.backend.entity.brand.Brand;
import com.ags.backend.entity.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByNameBrand(String nameBrand);
}
