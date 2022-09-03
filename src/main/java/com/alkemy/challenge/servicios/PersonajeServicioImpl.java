package com.alkemy.challenge.servicios;

import com.alkemy.challenge.dto.PersonajeDTO;
import com.alkemy.challenge.excepciones.ResourceNotFoundException;
import com.alkemy.challenge.modelos.Personaje;
import com.alkemy.challenge.repositorios.PersonajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonajeServicioImpl implements PersonajeServicio {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Override
    public PersonajeDTO crearPersonaje(PersonajeDTO personajeDTO) {
        Personaje personaje = mapearEntidad(personajeDTO);
        Personaje nuevoPersonaje = personajeRepositorio.save(personaje);

        PersonajeDTO personajeRespuesta = mapearDTO(nuevoPersonaje);
        return personajeRespuesta;
    }

    @Override
    public List<PersonajeDTO> obtenerTodosLosPersonajes() {
        List<Personaje> personajes = personajeRepositorio.findAll();
        return personajes.stream().map(personaje -> mapearDTO(personaje)).collect(Collectors.toList());
    }

    @Override
    public PersonajeDTO obtenerPersonajePorId(long id) {
        Personaje personaje = personajeRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));
        return mapearDTO(personaje);
    }

    @Override
    public PersonajeDTO actualizarPersonaje(PersonajeDTO personajeDTO, long id) {
        Personaje personaje = personajeRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));

        personaje.setImagen(personajeDTO.getImagen());
        personaje.setPeso(personajeDTO.getPeso());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setPeliculasSeries(personajeDTO.getPeliculasSeries());
        personaje.setHistoria(personajeDTO.getHistoria());

        Personaje personajeActualizado = personajeRepositorio.save(personaje);
        return mapearDTO(personajeActualizado);
    }

    @Override
    public void eliminarPersonaje(long id) {
        Personaje personaje = personajeRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));
        personajeRepositorio.delete(personaje);
    }

    //Convierte Entidad a DTO
    private PersonajeDTO mapearDTO(Personaje personaje) {
        PersonajeDTO personajeDTO = new PersonajeDTO();

        personajeDTO.setId(personaje.getId());
        personajeDTO.setImagen(personaje.getImagen());
        personajeDTO.setPeso(personaje.getPeso());
        personajeDTO.setNombre(personaje.getNombre());
        personajeDTO.setPeliculasSeries(personaje.getPeliculasSeries());
        personajeDTO.setHistoria(personaje.getHistoria());

        return personajeDTO;
    }

    //Convierte de DTO a Entidad
    private Personaje mapearEntidad(PersonajeDTO personajeDTO) {
        Personaje personaje = new Personaje();

        personaje.setImagen(personajeDTO.getImagen());
        personaje.setPeso(personajeDTO.getPeso());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setPeliculasSeries(personajeDTO.getPeliculasSeries());
        personaje.setHistoria(personajeDTO.getHistoria());

        return personaje;
    }
}