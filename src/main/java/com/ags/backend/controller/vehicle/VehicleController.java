package com.ags.backend.controller.vehicle;

import com.ags.backend.dto.vehicle.VehicleDto;
import com.ags.backend.entity.vehicle.Vehicle;
import com.ags.backend.message.MessageResponse;
import com.ags.backend.repository.vehicle.VehicleRepository;
import com.ags.backend.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleRepository vehicleRepository;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> uploadFile(@RequestPart MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Error is necessary to load the file"));
        }
        vehicleService.process(file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("file to loaded successfully."));
    }
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<List<Vehicle>> findAllVehicle(@RequestBody VehicleDto vehicleDto){
        return ResponseEntity.ok()
                .body(vehicleService.getAll(vehicleDto));
    }
}
