package com.api_rest.dbz.modelos;

import javax.persistence.*;

@Entity
@Table(name = "peliculasSeries")
public class PeliculaSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private Integer calificacion;
}
