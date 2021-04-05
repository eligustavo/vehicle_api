package com.elivelton.examechunnin.api.exceptions.handler;

public class BrandNotFoundException extends Exception {

    public BrandNotFoundException(String brand) {
        super(String.format("Vehicle with brand %s not found in the system.", brand));
    }

    public BrandNotFoundException(Long id) {
        super(String.format("Brand with id %s not found in the system.", id));
    }

}
