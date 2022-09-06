package com.api_rest.dbz.modelos;

import javax.persistence.*;

@Entity
@Table(name = "dialogos")
public class Dialogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    @Column(name = "audio",nullable = false)
    private String audio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personaje_id",nullable = false)
    private Personaje personaje;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Dialogo() {
        super();
    }

    public Dialogo(long id, String nombre, String descripcion, String audio, Personaje personaje) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.audio = audio;
        this.personaje = personaje;
    }
}
