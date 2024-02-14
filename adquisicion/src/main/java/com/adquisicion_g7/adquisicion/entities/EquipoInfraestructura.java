package com.adquisicion_g7.adquisicion.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "equipoInfraestructura")
public class EquipoInfraestructura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_equipo_id")
    private TipoEquipo tipoEquipo;

    @Column(name = "fecha_incorporacion" )
    private String fechaIncorporacion;

    @Column(name = "monto")
    private Float monto;

    @Column(name = "descripcion" )
    private String descripcion;

    @Column(name = "numero_serie")
    private String  numeroSerie;

    @Column(name = "eliminada")
    private Boolean eliminada;
}
