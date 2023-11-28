package com.gda.java.adquisiciones.entity;

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
@Table(name = "tipo_material")
public class TipoMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreTipoMaterial;

    // Constructor
    public TipoMaterial(String nombreTipoMaterial) {
        this.nombreTipoMaterial = nombreTipoMaterial;
    }

    // Métodos getters y setters

    public String getNombreTipoMaterial() {
        return nombreTipoMaterial;
    }

    public void setNombreTipoMaterial(String nombreTipoMaterial) {
        this.nombreTipoMaterial = nombreTipoMaterial;
    }

    public Long getIdTipoMaterial() {

        return id;
    }

    public void setIdTipoMaterial(Long idTipoMaterial) {

        this.id = idTipoMaterial;
    }
}































/*
package com.gda.java.adquisiciones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tipo_material")

public class TipoMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "nombre_tipo_material")
    private String nombreTipoMaterial;

   public String getNombreTipoMaterial(String nombreTipoMaterial) {
        this.nombreTipoMaterial = nombreTipoMaterial;
    }

    public long getIdTipoMaterial() {
        return 0;
    }

    /*public TipoMaterial(TipoMaterial tipoMaterial) {
        this.nombreTipoMaterial = nombreTipoMaterial;
    }*/

