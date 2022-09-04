package com.api_rest.dbz.modelos;

import javax.persistence.*;

@Entity
@Table(name = "personajes")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "peso", nullable = false)
    private Integer peso;

    @Column(name = "historia", nullable = false)
    private String historia;

    @Column(name = "peliculasSeries", nullable = false)
    private String peliculasSeries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getPeliculasSeries() {
        return peliculasSeries;
    }

    public void setPeliculasSeries(String peliculasSeries) {
        this.peliculasSeries = peliculasSeries;
    }

    public Personaje() {
        super();
    }

    public Personaje(Long id, String imagen, String nombre, Integer peso, String historia, String peliculasSeries) {
        super();
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.peso = peso;
        this.historia = historia;
        this.peliculasSeries = peliculasSeries;
    }
}
