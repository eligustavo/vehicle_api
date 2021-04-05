package com.elivelton.examechunnin.api.controller;

import com.elivelton.examechunnin.api.exceptions.handler.BrandNotFoundException;
import com.elivelton.examechunnin.api.exceptions.handler.VehicleAlreadyRegisteredException;
import com.elivelton.examechunnin.domain.entity.Vehicle;
import com.elivelton.examechunnin.domain.service.VehicleService;
import com.elivelton.examechunnin.dto.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public List<VehicleDTO> listAll() {
        return vehicleService.listAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleDTO create(@RequestBody @Valid VehicleDTO vehicleDTO) throws VehicleAlreadyRegisteredException {
        return vehicleService.create(vehicleDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws BrandNotFoundException {
        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody VehicleDTO vehicleDTO) {
        vehicleService.replace(vehicleDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
