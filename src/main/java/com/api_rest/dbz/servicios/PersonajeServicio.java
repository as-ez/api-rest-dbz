package com.api_rest.dbz.servicios;

import com.api_rest.dbz.dto.PersonajeDTO;
import com.api_rest.dbz.dto.PersonajeRespuesta;

public interface PersonajeServicio {
    public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO);

    public PersonajeRespuesta obtenerTodosLosPersonajes(int numeroDePagina,int medidadDePagina,String ordenarPor,String sortDir);

    public PersonajeDTO obtenerPersonajePorId(long id);

    public PersonajeDTO actualizarPersonaje(PersonajeDTO personajeDTO, long id);

    public void eliminarPersonaje(long id);
}
