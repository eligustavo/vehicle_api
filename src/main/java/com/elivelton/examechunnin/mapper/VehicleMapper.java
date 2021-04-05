package com.elivelton.examechunnin.mapper;

import com.elivelton.examechunnin.domain.entity.Vehicle;
import com.elivelton.examechunnin.dto.VehicleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Vehicle toModel(VehicleDTO vehicleDTO);

    VehicleDTO toDTO(Vehicle vehicle);

}
