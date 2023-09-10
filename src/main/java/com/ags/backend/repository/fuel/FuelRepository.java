package com.ags.backend.repository.fuel;

import com.ags.backend.entity.fuel.Fuel;
import com.ags.backend.entity.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Integer> {
    Optional<Fuel> findByNameFuel(@Param("nameFuel") String nameFuel);

    boolean existsByNameFuel(@Param("nameFuel") String nameFuel);
    @Query(value = "SELECT count(id_fuel) FROM ags.Fuel b WHERE b.name_fuel=:name_fuel",
            nativeQuery = true)
    int existsRegisteredFuel(@Param("name_fuel") String name_fuel);

    @Query("SELECT f.idFuel FROM Fuel f WHERE f.nameFuel=:nameFuel")
    int findIdFuel(@Param("nameFuel") String nameFuel);
}
