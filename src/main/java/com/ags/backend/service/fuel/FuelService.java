package com.ags.backend.service.fuel;

import com.ags.backend.dto.fuel.FuelDto;
import com.ags.backend.dto.vehicle.VehicleDto;
import com.ags.backend.entity.fuel.Fuel;
import com.ags.backend.entity.vehicle.Vehicle;
import com.ags.backend.repository.fuel.FuelRepository;
import com.ags.backend.repository.vehicle.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelService {
    @Autowired
    private FuelRepository fuelRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ModelMapper modelMapper;
    public List<FuelDto> findAllFull() {
        return fuelRepository.findAll()
                .stream().map(m -> modelMapper.map(m, FuelDto.class))
                .collect(Collectors.toList());
    }
}
