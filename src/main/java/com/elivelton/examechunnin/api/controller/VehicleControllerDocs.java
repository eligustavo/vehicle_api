package com.elivelton.examechunnin.api.controller;

import com.elivelton.examechunnin.api.exceptions.handler.BrandNotFoundException;
import com.elivelton.examechunnin.api.exceptions.handler.VehicleAlreadyRegisteredException;
import com.elivelton.examechunnin.dto.VehicleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Manages vehicles")
public interface VehicleControllerDocs {

    @ApiOperation(value = "Vehicle creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success vehicle creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    VehicleDTO create(VehicleDTO vehicleDTO) throws VehicleAlreadyRegisteredException;

    @ApiOperation(value = "Returns vehicle found by a given brand name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success vehicle found in the syste,"),
            @ApiResponse(code = 404, message = "Vehicle with given name not found.")
    })
    VehicleDTO findByBrand(VehicleDTO vehicleDTO) throws VehicleAlreadyRegisteredException;

    @ApiOperation(value = "Returns a list of all vehicles registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all beers registered in the system"),
    })
    List<VehicleDTO> listAll() ;

    @ApiOperation(value = "Delete a vehicle found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success vehicle deleted in the system"),
            @ApiResponse(code = 404, message = "Vehicle with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws BrandNotFoundException;

    @ApiOperation(value = "Replace data from a vehicle")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success vehicle when data is updated in the system"),
            @ApiResponse(code = 404, message = "Vehicle with given id not found.")
    })
    void replace(@PathVariable Long id) throws BrandNotFoundException;


}
