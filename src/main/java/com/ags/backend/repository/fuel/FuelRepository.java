package com.ags.backend.repository.fuel;

import com.ags.backend.entity.fuel.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Integer> {
    Optional<Fuel> findByNameFuel(String nameFuel);

    String sql = """
            SELECT
                name_brand,name_model,id_fuel, name_fuel,price_vehicle tract_vehicle,trans_vehicle,usage_vehicle  
            FROM 
                ags.brand b, ags.model m, ags.fuel f, ags.vehicle v   
            WHERE 
                b.id_brand = m.brand_id AND m.id_model = v.model_id AND f.id_fuel = v.fuel_id AND f.name_fuel = ?1
            """;
    @Query(value = sql, nativeQuery = true)
    List<Fuel> findByFullVehicle(String nameFuel);
    boolean existsByNameFuel(String nameFuel);



}
