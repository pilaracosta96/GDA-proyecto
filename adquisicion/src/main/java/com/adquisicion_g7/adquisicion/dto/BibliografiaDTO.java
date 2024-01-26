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
public class BibliografiaDTO {
    private String titulo;
    private String nombreAutor;
    private String apellidoAutor;
    private Long anioPublicacion;

    @NotBlank
    private String editorial;
    private Long isbn;
    private Long issn;
    private Float monto;
    private Boolean eliminada;

    @NotBlank
    private String tipoMaterial;
}
