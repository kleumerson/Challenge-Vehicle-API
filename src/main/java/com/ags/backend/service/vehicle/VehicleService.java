package com.ags.backend.service.vehicle;

import com.ags.backend.dto.brand.BrandDto;
import com.ags.backend.dto.fuel.FuelDto;
import com.ags.backend.dto.model.ModelDto;
import com.ags.backend.dto.vehicle.VehicleDto;
import com.ags.backend.entity.brand.Brand;
import com.ags.backend.entity.fuel.Fuel;
import com.ags.backend.entity.model.Model;
import com.ags.backend.entity.vehicle.Vehicle;
import com.ags.backend.repository.brand.BrandRepository;
import com.ags.backend.repository.fuel.FuelRepository;
import com.ags.backend.repository.model.ModelRepository;
import com.ags.backend.repository.vehicle.VehicleRepository;
import com.ags.backend.specification.VehicleSpecification;
import com.ags.backend.util.Util;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

    public void process(MultipartFile file) throws IOException {
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

            CSVReader reader = new CSVReaderBuilder(bufferedReader)
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .withSkipLines(1)
                    .build();

            String[] rows = null;
            while ((rows = reader.readNext()) != null) {

                String nameBrand =
                        rows[0].trim();
                String nameModel =
                        rows[1].trim();
                String nameFuel =
                        rows[2].trim();
                String nameTransmission =
                        rows[3].trim();
                String nameTraction =
                        rows[4].trim();
                BigDecimal namePrince =
                        Util.formatBigDecimalWithDecimalPlace(rows[5].trim());
                int nameUseTime =
                        Integer.parseInt(rows[6].trim());

                save(nameBrand, nameModel, nameFuel, nameTransmission, nameTraction, namePrince, nameUseTime);
            }
            bufferedReader.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.NEVER)
    public void save(String brand, String model, String fuel, String transmission,
                     String traction, BigDecimal prince, int useTime){

        BrandDto brandDto = new BrandDto();
        int contBrand = brandRepository
                .existsRegisteredBrand(brand);

        if (contBrand == 0) {
            brandDto.setNameBrand(brand);
            brandRepository
                    .save(modelMapper.map(
                            brandDto, Brand.class));
        }

        int idBrand = brandRepository
                .findIdBrand(brand);
        brandDto.setIdBrand(idBrand);

        ModelDto modelDto = new ModelDto();
        int contModel = modelRepository
                .existsRegisteredModel(model);

        if (contModel == 0){
            modelDto.setModelName(model);
            modelDto.setBrand(brandDto);
            modelRepository
                    .save(modelMapper.map(
                            modelDto, Model.class));
        }

        int idModel = modelRepository
                .findIdModel(model);
        modelDto.setIdModel(idModel);

        FuelDto fuelDto = new FuelDto();
        int contFuel = fuelRepository.existsRegisteredFuel(fuel);
        if (contFuel == 0) {
            fuelDto.setNameFuel(fuel);
            fuelRepository
                    .save(modelMapper.map(
                            fuelDto, Fuel.class));
        }

        int id_fuel = fuelRepository
                .findIdFuel(fuel);
        fuelDto.setIdFuel(id_fuel);

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setModel(modelDto);
        vehicleDto.setIdFuel(fuelDto);
        vehicleDto.setTransmission(transmission);
        vehicleDto.setTraction(transmission);
        vehicleDto.setPrice(prince);
        vehicleDto.setUsageTime(useTime);

        vehicleRepository
                .save(modelMapper.map(
                        vehicleDto, Vehicle.class));
    }
    public List<Vehicle> getAll(VehicleDto vehicleDto){

        Vehicle vehicle = modelMapper
                .map(vehicleDto, Vehicle.class);

        VehicleSpecification vehicleSpecification
                = new VehicleSpecification();

        return new ArrayList<>(vehicleSpecification.getAll(vehicleDto));
    }
}

