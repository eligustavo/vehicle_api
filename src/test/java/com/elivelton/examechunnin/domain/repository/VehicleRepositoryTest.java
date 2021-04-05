package com.elivelton.examechunnin.domain.repository;

import com.elivelton.examechunnin.domain.entity.Vehicle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
@DisplayName("Testes para Vehicle Repository")
class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    @DisplayName("Save persists vehicle when sucessful")
    void save_PersistVehicle_WhenSucessful() {
        Vehicle toBeSaved = createVehicle();

        Vehicle saved = this.vehicleRepository.save(toBeSaved);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isNotNull();
        Assertions.assertThat(saved.getBrand()).isNotNull();
//        Assertions.assertThat(saved.getModel()).isNotNull();
//        Assertions.assertThat(saved.getVehicleType()).isNotNull();
//        Assertions.assertThat(saved.getPrice()).isNotNull();
//        Assertions.assertThat(saved.getYear()).isNotNull();

        Assertions.assertThat(saved.getBrand()).isEqualTo(toBeSaved.getBrand());
//        Assertions.assertThat(saved.getVehicleType()).isEqualTo(toBeSaved.getVehicleType());
//        Assertions.assertThat(saved.getModel()).isEqualTo(toBeSaved.getModel());
//        Assertions.assertThat(saved.getPrice()).isEqualTo(toBeSaved.getPrice());
//        Assertions.assertThat(saved.getYear()).isEqualTo(toBeSaved.getYear());
    }



    private Vehicle createVehicle() {
        return Vehicle.builder()
                .brand("Wolks")
                .vehicleType("passeio")
                .model("gol")
                .price(BigDecimal.valueOf(150000))
                .year(2010)
                .build();
    }
}