package com.adquisicion_g7.adquisicion.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonNaming(PropertyNamingStrategy.class.SnakeCaseStrategy.class)
public class MensajeDTO {
    private String mensaje;
}
