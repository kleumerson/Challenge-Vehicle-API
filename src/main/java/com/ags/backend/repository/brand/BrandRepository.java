package com.ags.backend.repository.brand;

import com.ags.backend.entity.brand.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query(value = "SELECT count(id_brand) FROM ags.Brand b WHERE b.name_brand=:nameBrand", nativeQuery = true)
    int existsRegisteredBrand(@Param("nameBrand") String nameBrand);
    @Query("SELECT b.idBrand FROM Brand b WHERE b.nameBrand=:nameBrand")
    int findIdBrand(String nameBrand);
}
