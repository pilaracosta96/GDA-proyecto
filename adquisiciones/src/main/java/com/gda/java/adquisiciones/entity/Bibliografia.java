package com.gda.java.adquisiciones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bibliografia")
public class Bibliografia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "nombre_autor")
    private String nombreAutor;

    @Column(name = "apellido_autor")
    private String apellidoAutor;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    @ManyToOne
    @JoinColumn(name = "editorial")
    private Editorial editorial;

    @Column(name = "isbn")
    private Long isbn;

    @Column(name = "issn")
    private Long issn;

    @Column(name = "monto")
    private Float monto;

    @ManyToOne
    @JoinColumn(name = "tipo_material")
    private TipoMaterial tipoMaterial;

    public Bibliografia(String titulo, String nombreAutor, String apellidoAutor, int anioPublicacion,
                        Editorial editorial, Long isbn, Long issn, double monto, TipoMaterial tipoMaterial) {
        this.titulo = titulo;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.isbn = isbn;
        this.issn = issn;
        this.monto = (float) monto;
        this.tipoMaterial = tipoMaterial;
    }
}
