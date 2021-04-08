package com.elivelton.examechunnin.domain.service;

import com.elivelton.examechunnin.api.exceptions.handler.BrandNotFoundException;
import com.elivelton.examechunnin.api.exceptions.handler.VehicleAlreadyRegisteredException;
import com.elivelton.examechunnin.builder.VehicleDTOBuilder;
import com.elivelton.examechunnin.domain.entity.Vehicle;
import com.elivelton.examechunnin.domain.repository.VehicleRepository;
import com.elivelton.examechunnin.dto.VehicleDTO;
import com.elivelton.examechunnin.mapper.VehicleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    private static final long INVALID_VEHICLE_ID = 1L;

    @Mock
    private VehicleRepository vehicleRepository;

    private VehicleMapper vehicleMapper = VehicleMapper.INSTANCE;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void whenVehicleInformedThenItShouldBeCreated() throws VehicleAlreadyRegisteredException {
        // given
        VehicleDTO expectedVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle expectedSavedVehicle = vehicleMapper.toModel(expectedVehicleDTO);

        //when
        when(vehicleRepository.findByBrand(expectedVehicleDTO.getBrand())).thenReturn(Optional.empty());
        when(vehicleRepository.save(expectedSavedVehicle)).thenReturn(expectedSavedVehicle);

        //then
        VehicleDTO createdVehicleDTO = vehicleService.create(expectedVehicleDTO);

        assertThat(createdVehicleDTO.getId(), is(equalTo(expectedVehicleDTO.getId())));
        assertThat(createdVehicleDTO.getBrand(), is(equalTo(expectedVehicleDTO.getBrand())));
        assertThat(createdVehicleDTO.getVehicleType(), is(equalTo(expectedVehicleDTO.getVehicleType())));

    }

    @Test
    void whenAlreadyRegisteredVehicleInformedThenAnExceptionShouldBeThrown() throws VehicleAlreadyRegisteredException {
        // given
        VehicleDTO expectedVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle duplicatedSavedVehicle = vehicleMapper.toModel(expectedVehicleDTO);

        // when
        when(vehicleRepository.findByBrand(expectedVehicleDTO.getBrand())).thenReturn(Optional.of(duplicatedSavedVehicle));

        // then
        assertThrows(VehicleAlreadyRegisteredException.class, () -> vehicleService.create(expectedVehicleDTO));
    }

    @Test
    void whenValidVehicleBrandIsGivenThenReturnAVehicle() throws BrandNotFoundException {
        // given
        VehicleDTO expectedFoundVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle expectedFoundVehicle = vehicleMapper.toModel(expectedFoundVehicleDTO);

        // when
        when(vehicleRepository.findByBrand(expectedFoundVehicle.getBrand())).thenReturn(Optional.of(expectedFoundVehicle));

        // the
        VehicleDTO foundVehicleDTO = vehicleService.findByBrand(expectedFoundVehicleDTO.getBrand());

        assertThat(foundVehicleDTO, is(equalTo(foundVehicleDTO)));

    }

    @Test
    void whenNotRegisteredVehicleBrandIsGivenThenThrowAnException() throws BrandNotFoundException {
        // given
        VehicleDTO expectedFoundVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        // when
        when(vehicleRepository.findByBrand(expectedFoundVehicleDTO.getBrand())).thenReturn(Optional.empty());

        // then
        assertThrows(BrandNotFoundException.class, () -> vehicleService.findByBrand(expectedFoundVehicleDTO.getBrand()));
    }

    @Test
    void whenListVehicleIsCalledThenReturnAListOfVehicles() {
        // given
        VehicleDTO expectedFoundVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle expectedFoundVehicle = vehicleMapper.toModel(expectedFoundVehicleDTO);

        //when
        when(vehicleRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundVehicle));

        //then
        List<VehicleDTO> foundListBeersDTO = vehicleService.listAll();

        assertThat(foundListBeersDTO, is(not(empty())));
        assertThat(foundListBeersDTO.get(0), is(equalTo(expectedFoundVehicleDTO)));
    }

    @Test
    void whenListVehicleIsCalledThenReturnAnEmptyListOfVehicles() {
        //when
        when(vehicleRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<VehicleDTO> foundListVehiclesDTO = vehicleService.listAll();

        assertThat(foundListVehiclesDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenABeerShouldBeDeleted() throws BrandNotFoundException {
        // given
        VehicleDTO expectedDeletedVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle expectedDeletedVehicle = vehicleMapper.toModel(expectedDeletedVehicleDTO);

        // when
        when(vehicleRepository.findById(expectedDeletedVehicleDTO.getId())).thenReturn(Optional.of(expectedDeletedVehicle));
        doNothing().when(vehicleRepository).deleteById(expectedDeletedVehicle.getId());

        // then
        vehicleService.delete(expectedDeletedVehicleDTO.getId());

        verify(vehicleRepository, times(1)).findById(expectedDeletedVehicleDTO.getId());
        verify(vehicleRepository, times(1)).deleteById(expectedDeletedVehicleDTO.getId());
    }

}