package com.elivelton.examechunnin.mapper;

import com.elivelton.examechunnin.domain.entity.Vehicle;
import com.elivelton.examechunnin.dto.VehicleDTO;
import com.elivelton.examechunnin.dto.VehicleDTO.VehicleDTOBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-07T09:21:49-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public Vehicle toModel(VehicleDTO vehicleDTO) {
        if ( vehicleDTO == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( vehicleDTO.getId() );
        vehicle.setVehicleType( vehicleDTO.getVehicleType() );
        vehicle.setModel( vehicleDTO.getModel() );
        vehicle.setBrand( vehicleDTO.getBrand() );
        vehicle.setYear( vehicleDTO.getYear() );
        vehicle.setPrice( vehicleDTO.getPrice() );

        return vehicle;
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
