package com.ags.backend.service.fuel;

import com.ags.backend.dto.fuel.FuelDto;
import com.ags.backend.entity.fuel.Fuel;
import com.ags.backend.exception.VehicleNotFoundException;
import com.ags.backend.repository.fuel.FuelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuelService {

    @Autowired
    private FuelRepository fuelRepository;
    @Autowired
    private ModelMapper modelMapper;
    public List<FuelDto> findAllFull() {
        return fuelRepository.findAll()
                .stream().map(m -> modelMapper.map(m, FuelDto.class))
                .collect(Collectors.toList());
    }
    public List<FuelDto> findByFuelVehicle(FuelDto fuelDto) {
        Optional<Fuel> optionalFuel = fuelRepository
                .findByNameFuel(fuelDto.getNameFuel());

        if (!optionalFuel.isPresent()) {
            throw new VehicleNotFoundException();
        }

        return fuelRepository.findByFullVehicle(fuelDto.getNameFuel())
                .stream().map(m -> modelMapper.map(m, FuelDto.class)).collect(Collectors.toList());
    }
}
