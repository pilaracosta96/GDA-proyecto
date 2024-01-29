package com.adquisicion_g7.adquisicion.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LicenciaSoftwareDTO {

    private String nombre;
    private Long anio;
    private Long fechaOtorgamiento;
    private Long fechaVencimiento;

    @NotBlank
    private String fabricante;
    private Float numeroRelease;
    private Float version;
    private Float monto;
}
