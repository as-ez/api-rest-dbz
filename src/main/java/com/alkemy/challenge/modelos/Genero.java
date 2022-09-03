package com.alkemy.challenge.modelos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "imagen",nullable = false)
    private String imagen;

    @OneToMany(mappedBy = "genero")
    List<PeliculaSerie> peliculasSeries;

}