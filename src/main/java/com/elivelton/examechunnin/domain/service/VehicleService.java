package com.elivelton.examechunnin.domain.service;

import com.elivelton.examechunnin.api.exceptions.BadRequestException;
import com.elivelton.examechunnin.api.exceptions.handler.BrandNotFoundException;
import com.elivelton.examechunnin.api.exceptions.handler.VehicleAlreadyRegisteredException;
import com.elivelton.examechunnin.domain.entity.Vehicle;
import com.elivelton.examechunnin.domain.repository.VehicleRepository;
import com.elivelton.examechunnin.dto.VehicleDTO;
import com.elivelton.examechunnin.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper = VehicleMapper.INSTANCE;

    public List<VehicleDTO> listAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public VehicleDTO findByBrand(String brand) throws BrandNotFoundException {
        Vehicle foundBrand = vehicleRepository.findByBrand(brand)
                .orElseThrow(() -> new BrandNotFoundException(brand));
        return vehicleMapper.toDTO(foundBrand);
    }

    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new BadRequestException("Not Found"));
    }

    public VehicleDTO create(VehicleDTO vehicleDTO) throws VehicleAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(vehicleDTO.getBrand());
        Vehicle vehicle = vehicleMapper.toModel(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(savedVehicle);
    }

    public void delete(Long id) throws BrandNotFoundException {
        verifyIfExists(id);
        vehicleRepository.delete(findById(id));
    }

    public void replace(VehicleDTO vehicleDTO) {
        Vehicle savedVehicle = findById(vehicleDTO.getId());
        Vehicle vehicle = VehicleMapper.INSTANCE.toModel(vehicleDTO);
        vehicle.setId(savedVehicle.getId());
        vehicleRepository.save(vehicle);
    }

    private void verifyIfIsAlreadyRegistered(String brand) throws VehicleAlreadyRegisteredException {
        Optional<Vehicle> optSavedVehicle = vehicleRepository.findByBrand(brand);
        if (optSavedVehicle.isPresent()) {
            throw new VehicleAlreadyRegisteredException(brand);
        }
    }

    public Vehicle verifyIfExists(Long id) throws BrandNotFoundException {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
    }

}
