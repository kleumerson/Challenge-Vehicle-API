package com.ags.backend.repository.model;

import com.ags.backend.entity.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByNameModel(String nameModel);
    @Query(value = "SELECT count(id_model) from ags.Model m where m.name_model=:nameModel", nativeQuery = true)
    int existsRegisteredModel(@Param("nameModel") String nameModel);

    @Query("SELECT m.idModel FROM Model m where m.nameModel=:nameModel")
    int findIdModel(String nameModel);
}
