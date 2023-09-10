package com.ags.backend.controller.flue;

import com.ags.backend.dto.fuel.FuelDto;
import com.ags.backend.service.fuel.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/fuel", method = RequestMethod.GET)
public class FuelController {
    @Autowired
    private FuelService fuelService;
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<FuelDto>> findAllFuel(){
        return ResponseEntity.ok().body(fuelService.findAllFull());
    }
}
