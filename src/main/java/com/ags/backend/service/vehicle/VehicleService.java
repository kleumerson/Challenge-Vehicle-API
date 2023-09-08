package com.ags.backend.service.vehicle;

import com.ags.backend.dto.brand.BrandDto;
import com.ags.backend.dto.fuel.FuelDto;
import com.ags.backend.dto.model.ModelDto;
import com.ags.backend.dto.vehicle.VehicleDto;
import com.ags.backend.entity.brand.Brand;
import com.ags.backend.entity.model.Model;
import com.ags.backend.entity.vehicle.Vehicle;
import com.ags.backend.exception.VehicleNotFoundException;
import com.ags.backend.repository.brand.BrandRepository;
import com.ags.backend.repository.fuel.FuelRepository;
import com.ags.backend.repository.model.ModelRepository;
import com.ags.backend.repository.vehicle.VehicleRepository;
import com.ags.backend.specification.SpecificationVehicle;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private FuelRepository fuelRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.NEVER)
    public void processFileVehicle(MultipartFile file) throws IOException {
        try{
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

            CSVReader reader = new CSVReaderBuilder(bufferedReader)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .withSkipLines(1)
                    .build();

            String[] rows = null;
            while ((rows = reader.readNext()) != null) {

                BrandDto brandDto = new BrandDto(rows[0]);
                //brandDto.setNameBrand(rows[0]);

                ModelDto modelDto = new ModelDto();
                modelDto.setModelName(rows[1]);
                modelDto.setBrand(brandDto);

                FuelDto fuelDto = new FuelDto();
                fuelDto.setNameFuel(rows[2]);

                VehicleDto vehicleDto = new VehicleDto();
                vehicleDto.setModel(modelDto);
                vehicleDto.setFuel(fuelDto);
                vehicleDto.setTransmission(rows[3]);
                vehicleDto.setTraction(rows[4]);
                vehicleDto.setPrice(null);
                vehicleDto.setUsageTime(Integer.parseInt(rows[6]));

                if (!brandRepository.existsByNameBrand(
                        brandDto.getNameBrand())){
                    brandRepository
                            .save(modelMapper.map(
                                    brandDto, Brand.class));
                }
                if (!modelRepository.existsByNameModel(
                        modelDto.getModelName())){
                    modelRepository
                            .save(modelMapper.map(
                                    modelDto, Model.class));
                }
                if (!fuelRepository.existsByNameFuel(
                        fuelDto.getNameFuel()))
                    brandRepository
                            .save(modelMapper.map(
                                    brandDto, Brand.class));
                vehicleRepository
                        .save(modelMapper.map(
                                vehicleDto, Vehicle.class));
            }

            bufferedReader.close();
            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<VehicleDto> findAllVehicle(VehicleDto vehicleDto){

        Specification<Vehicle> vehicleSpecification = null;

        if (vehicleDto.getTransmission() != null && vehicleDto.getUsageTime() != 0
                && vehicleDto.getTraction() != null){

            vehicleSpecification = Specification.where(SpecificationVehicle.usageTime(vehicleDto.getUsageTime()))
                    .and(SpecificationVehicle.transmission(vehicleDto.getTransmission()));
        }
        if ((vehicleDto.getUsageTime() != 0) && (vehicleDto.getTransmission() != null) && (vehicleDto.getTraction() != null)
                && ((vehicleDto.getPrice() != null) && (vehicleDto.getFinalPrice() != null))){

            vehicleSpecification = Specification.where(SpecificationVehicle.usageTime(vehicleDto.getUsageTime()))
                    .and(SpecificationVehicle.transmission(vehicleDto.getTransmission())
                            .and(SpecificationVehicle.price(vehicleDto.getPrice(), vehicleDto.getFinalPrice())
                                    .and(SpecificationVehicle.transmission(vehicleDto.getTraction()))));
        }
        assert vehicleSpecification != null;
        List<VehicleDto> listVehicleDto = vehicleRepository.findAll(vehicleSpecification)
                .stream().map(m-> modelMapper.map(m, VehicleDto.class))
                .collect(Collectors.toList());
        if (listVehicleDto.isEmpty()) {
            throw new VehicleNotFoundException();
        }
        return listVehicleDto;
    }
}

