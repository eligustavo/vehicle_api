package com.elivelton.examechunnin.mapper;

import com.elivelton.examechunnin.domain.entity.Vehicle;
import com.elivelton.examechunnin.domain.entity.Vehicle.VehicleBuilder;
import com.elivelton.examechunnin.dto.VehicleDTO;
import com.elivelton.examechunnin.dto.VehicleDTO.VehicleDTOBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-05T10:05:35-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public Vehicle toModel(VehicleDTO vehicleDTO) {
        if ( vehicleDTO == null ) {
            return null;
        }

        VehicleBuilder vehicle = Vehicle.builder();

        vehicle.id( vehicleDTO.getId() );
        vehicle.vehicleType( vehicleDTO.getVehicleType() );
        vehicle.model( vehicleDTO.getModel() );
        vehicle.brand( vehicleDTO.getBrand() );
        vehicle.year( vehicleDTO.getYear() );
        vehicle.price( vehicleDTO.getPrice() );

        return vehicle.build();
    }

    @Override
    public VehicleDTO toDTO(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleDTOBuilder vehicleDTO = VehicleDTO.builder();

        vehicleDTO.id( vehicle.getId() );
        vehicleDTO.vehicleType( vehicle.getVehicleType() );
        vehicleDTO.model( vehicle.getModel() );
        vehicleDTO.brand( vehicle.getBrand() );
        vehicleDTO.year( vehicle.getYear() );
        vehicleDTO.price( vehicle.getPrice() );

        return vehicleDTO.build();
    }
}
