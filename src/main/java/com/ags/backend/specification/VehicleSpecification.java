package com.ags.backend.specification;

import com.ags.backend.dto.vehicle.VehicleDto;
import com.ags.backend.entity.vehicle.Vehicle;
import com.ags.backend.util.JPAUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VehicleSpecification {
    public List<Vehicle> getAll(VehicleDto vehicleParams) {

        Session session = (Session) JPAUtil.getEntityManager();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);
        Root<Vehicle> vehicle = query.from(Vehicle.class);

        List<Predicate> predicates = new ArrayList<>();

        if (vehicleParams.getTransmission() != null){
            predicates.add(criteriaBuilder.equal(vehicle.get("transmission"),
                    vehicleParams.getTransmission()));
        }
        if (vehicleParams.getUsageTime() != 0){
            predicates.add(criteriaBuilder.equal(vehicle.get("usageTime"),
                    vehicleParams.getUsageTime()));
        }
        if (vehicleParams.getTraction() != null){
            predicates.add(criteriaBuilder.equal(vehicle.get("traction"),
                    vehicleParams.getTraction()));
        }

        if (vehicleParams.getIdFuel() != null){
            predicates.add(criteriaBuilder.equal(vehicle.get("idFuel"),
                    vehicleParams.getIdFuel()));
        }

        if (vehicleParams.getPrice().compareTo(BigDecimal.ZERO) > 0
                && vehicleParams.getFinalPrice().compareTo(BigDecimal.ZERO) > 0) {
            predicates.add(criteriaBuilder.between(vehicle.get("price"),
                    vehicleParams.getPrice(), vehicleParams.getFinalPrice()));
        }

        if (!predicates.isEmpty()){
            query.where(predicates.toArray(Predicate[]::new));
        }

        TypedQuery<Vehicle> typedQuery = session.createQuery(query);

        return typedQuery.getResultList();
    }
}
