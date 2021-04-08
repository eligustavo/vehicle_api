package com.elivelton.examechunnin.api.controller;

import com.elivelton.examechunnin.api.exceptions.handler.BrandNotFoundException;
import com.elivelton.examechunnin.builder.VehicleDTOBuilder;
import com.elivelton.examechunnin.domain.service.VehicleService;
import com.elivelton.examechunnin.dto.VehicleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static com.elivelton.examechunnin.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VehicleControllerTest {

    private static final String VEHICLE_API_URL_PATH = "/api/v1/vehicles";
    private static final long VALID_VEHICLE_ID = 1L;
    private static final long INVALID_VEHICLE_ID = 2l;

    private MockMvc mockMvc;

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAVehicleIsCreated() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        // when
        when(vehicleService.create(vehicleDTO)).thenReturn(vehicleDTO);

        // then
        mockMvc.perform(post(VEHICLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vehicleDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vehicleType", is(vehicleDTO.getVehicleType())))
                .andExpect(jsonPath("$.brand", is(vehicleDTO.getBrand())))
                .andExpect(jsonPath("$.model", is(vehicleDTO.getModel().toString())));

    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAErrorIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        vehicleDTO.setBrand(null);

        // then
        mockMvc.perform(post(VEHICLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vehicleDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidBrandOkStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        when(vehicleService.findByBrand(vehicleDTO.getBrand())).thenReturn(vehicleDTO);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(VEHICLE_API_URL_PATH + "/" + vehicleDTO.getBrand())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleType", is(vehicleDTO.getVehicleType())))
                .andExpect(jsonPath("$.brand", is(vehicleDTO.getBrand())))
                .andExpect(jsonPath("$.model", is(vehicleDTO.getModel().toString())));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredBrandThenNotFoundStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        //when
        when(vehicleService.findByBrand(vehicleDTO.getBrand())).thenThrow(BrandNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(VEHICLE_API_URL_PATH + "/" + vehicleDTO.getBrand())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithVehiclesIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        //when
        when(vehicleService.listAll()).thenReturn(Collections.singletonList(vehicleDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(VEHICLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vehicleType", is(vehicleDTO.getVehicleType())))
                .andExpect(jsonPath("$[0].brand", is(vehicleDTO.getBrand())))
                .andExpect(jsonPath("$[0].model", is(vehicleDTO.getModel().toString())));
    }

    @Test
    void whenGETListWithoutVehiclesIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        //when
        when(vehicleService.listAll()).thenReturn(Collections.singletonList(vehicleDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(VEHICLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        //when
        doNothing().when(vehicleService).delete(vehicleDTO.getId());

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(VEHICLE_API_URL_PATH + "/" + vehicleDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        //when
        doThrow(BrandNotFoundException.class).when(vehicleService).delete(INVALID_VEHICLE_ID);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(VEHICLE_API_URL_PATH + "/" + INVALID_VEHICLE_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}