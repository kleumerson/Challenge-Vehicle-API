package com.ags.backend.controller.flue;

import com.ags.backend.dto.fuel.FuelDto;
import com.ags.backend.service.fuel.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/fuel", method = RequestMethod.GET)
public class FuelController {
    @Autowired
    private FuelService fuelService;
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<FuelDto>> findAllFuel(){

        return new ResponseEntity<>(fuelService.findAllFull(), HttpStatus.OK);
    }
    @RequestMapping(value = "/fuel", method = RequestMethod.POST)
    public ResponseEntity<List<FuelDto>> findByFuelVehicle(@RequestBody FuelDto fuelDto){
        return new ResponseEntity<>(fuelService.findByFuelVehicle(fuelDto), HttpStatus.OK);
    }
}
