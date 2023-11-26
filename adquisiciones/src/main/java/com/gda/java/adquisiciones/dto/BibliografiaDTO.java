package com.gda.java.adquisiciones.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gda.java.adquisiciones.entity.Editorial;
import com.gda.java.adquisiciones.entity.TipoMaterial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BibliografiaDTO {
    private String titulo;
    private String nombreAutor;
    private String apellidoAutor;
    private Integer anioPublicacion;
    private Editorial editorial;
    private Long isbn;
    private Long issn;
    private Float monto;
    private TipoMaterial tipomaterial;

    public void setTipoMaterial(String nombreTipoMaterial) {
    }

    public void setEditorial(String nombreEditorial) {
    }
}



  /*  public void setEditorial(String nombreEditorial) {
        this.setEditorial(nombreEditorial);
    }

    public void setTipomaterial(String nombreTipoMaterial) {
        this.setTipoMaterial(nombreTipoMaterial);
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.setTipoMaterial(tipoMaterial);
    }*/