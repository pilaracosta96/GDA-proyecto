package com.adquisicion_g7.adquisicion.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "editorial")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_editorial" )
    private String nombreEditorial;

    public Editorial (String nombreEditorial){
        this.nombreEditorial= nombreEditorial;
    }
}