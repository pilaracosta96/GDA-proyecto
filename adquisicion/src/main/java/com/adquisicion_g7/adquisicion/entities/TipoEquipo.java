package com.adquisicion_g7.adquisicion.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipo_equipo")
public class TipoEquipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nombre_tipo_equipo")
    private String nombreTipoEquipo;

    @Column (name = "cantidad")
    private Long cantidad;

    @Column (name = "precio_unitario")
    private Float precioUnitario;

    public TipoEquipo(String nombreTipoEquipo, Long cantidad, Float precioUnitario) {
        this.nombreTipoEquipo = nombreTipoEquipo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
}
