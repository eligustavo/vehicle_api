package com.elivelton.examechunnin.builder;

import com.elivelton.examechunnin.dto.VehicleDTO;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class VehicleDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String vehicleType = "motocicleta";

    @Builder.Default
    private String brand = "honda";

    @Builder.Default
    private String model = "cb600";

    @Builder.Default
    private Integer year = 2015;

    @Builder.Default
    private BigDecimal price = BigDecimal.valueOf(15000);

    public VehicleDTO toVehicleDTO() {
        return new VehicleDTO(id,
                vehicleType,
                brand,
                model,
                year,
                price);
    }

}
