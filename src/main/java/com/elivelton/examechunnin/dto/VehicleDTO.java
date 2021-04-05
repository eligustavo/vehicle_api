package com.elivelton.examechunnin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private Long id;

    @NotNull
    private String vehicleType;
    @NotNull
    private String model;
    @NotNull
    private String brand;
    @NotNull
    private Integer year;
    @NotNull
    private BigDecimal price;

}
