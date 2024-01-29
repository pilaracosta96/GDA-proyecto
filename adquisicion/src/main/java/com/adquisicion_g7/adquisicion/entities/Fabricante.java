package com.adquisicion_g7.adquisicion.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "fabricante")
public class Fabricante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_fabricante" )
    private String nombreFabricante;

    public Fabricante (String nombreFabricante){
            this.nombreFabricante= nombreFabricante;
        }
    }

