package com.adquisicion_g7.adquisicion.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "licenciaSoftware")
public class LicenciaSoftware {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre" )
    private String nombre;

    @Column(name = "anio")
    private String anio;

    @Column(name = "fecha_otorgamiento" )
    private String fechaOtorgamiento;

    @Column(name = "fecha_vencimiento" )
    private String fechaVencimiento;


    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;

    @Column(name = "numero_release")
    private String numeroRelease;

    @Column(name = "version")
    private String version;

    @Column(name = "monto")
    private Float monto;

    @Column(name = "numero_serie", unique = true)
    private String numeroSerie;

    @Column(name = "eliminada")
    private Boolean eliminada;

}
