package com.adquisicion_g7.adquisicion.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bibliografia")
public class Bibliografia {
    @Id
    /*@SequenceGenerator(name = "bibliografia_sequence",
                        sequenceName = "bibliografia_sequence",
                        allocationSize = 1
     )
    @GeneratedValue(strategy = SEQUENCE,
                generator = "bibliografia_sequence"
    )*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo" )
    private String titulo;

    @Column(name = "nombre_autor" )
    private String nombreAutor;

    @Column(name = "apellido_autor" )
    private String apellidoAutor;

    @Column(name = "anio_publicacion" )
    private Long anioPublicacion;

    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @Column(name = "isbn", length = 13, nullable = false, unique = true )
    private Long isbn;

    @Column(name = "issn" )
    private Long issn;

    @Column(name = "monto" )
    private Float monto;

    @Column(name = "eliminada")
    private boolean eliminada;

    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_material_id")
    private TipoMaterial tipoMaterial;

}