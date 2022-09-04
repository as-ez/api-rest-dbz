package com.api_rest.dbz.dto;

public class PersonajeDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private Integer peso;
    private String historia;
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

    public PersonajeDTO() {
        super();
    }
}
