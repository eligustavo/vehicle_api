package com.elivelton.examechunnin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String vehicleType;
    @NotNull
    @Size(min = 1, max = 200)
    private String model;
    @NotNull
    @Size(min = 1, max = 200)
    private String brand;
    @NotNull
    private Integer year;
    @NotNull
    private BigDecimal price;

}
