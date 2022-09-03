package com.alkemy.challenge.servicios;

import com.alkemy.challenge.dto.PersonajeDTO;

import java.util.List;

public interface PersonajeServicio {
    public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO);

    public List<PersonajeDTO> obtenerTodosLosPersonajes();

    public PersonajeDTO obtenerPersonajePorId(long id);

    public PersonajeDTO actualizarPersonaje(PersonajeDTO personajeDTO, long id);

    public void eliminarPersonaje(long id);
}
