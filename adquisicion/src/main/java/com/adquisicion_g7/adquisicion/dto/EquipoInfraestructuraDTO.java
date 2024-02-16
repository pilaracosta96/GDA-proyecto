package com.adquisicion_g7.adquisicion.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EquipoInfraestructuraDTO {

    private String fechaIncorporacion;
    private Float monto;
    private String descripcion;

    @NotBlank
    private String tipoEquipo;
    private Boolean eliminada;

    @NotNull
    private Long cantidad;

    @NotNull
    private Float precioUnitario;
    @NotNull
    private String numeroSerie;
}
