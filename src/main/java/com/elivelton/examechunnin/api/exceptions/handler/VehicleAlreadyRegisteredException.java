package com.elivelton.examechunnin.api.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VehicleAlreadyRegisteredException extends Exception{

    public VehicleAlreadyRegisteredException(String brand) {
        super(String.format("Vehicle with brand %s already registered in the system.", brand));
    }

}
