package com.elivelton.examechunnin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private Long id;

    @NotBlank(message = "Este campo não pode ser nulo")
    private String vehicleType;
    @NotBlank(message = "Este campo não pode ser nulo")
    private String model;
    @NotBlank(message = "Este campo não pode ser nulo")
    private String brand;
    private Integer year;
    private BigDecimal price;

}
