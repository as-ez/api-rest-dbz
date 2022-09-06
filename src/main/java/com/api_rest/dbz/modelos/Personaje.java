package com.api_rest.dbz.modelos;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "personaje",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Dialogo> dialogos = new HashSet<>();

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
    }
}
