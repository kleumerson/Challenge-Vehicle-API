package com.ags.backend.specification;

import com.ags.backend.entity.vehicle.Vehicle;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class SpecificationVehicle {
    public static Specification<Vehicle> price(BigDecimal initialPrice,
                                               BigDecimal finalPrice) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), initialPrice, finalPrice));
    }
    public static Specification<Vehicle> transmission(String transmission) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("transmission"), transmission.toUpperCase()));
    }
    public static Specification<Vehicle> traction(String traction) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("traction"), traction));
    }
    public static Specification<Vehicle> usageTime (int usageTime){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("usageTime"), usageTime));
    }
}
