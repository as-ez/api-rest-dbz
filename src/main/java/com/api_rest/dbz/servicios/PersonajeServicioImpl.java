package com.api_rest.dbz.servicios;

import com.api_rest.dbz.dto.PersonajeDTO;
import com.api_rest.dbz.dto.PersonajeRespuesta;
import com.api_rest.dbz.excepciones.ResourceNotFoundException;
import com.api_rest.dbz.modelos.Personaje;
import com.api_rest.dbz.repositorios.PersonajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PersonajeRespuesta obtenerTodosLosPersonajes(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina,medidaDePagina,sort);
        Page<Personaje> personajes = personajeRepositorio.findAll(pageable);

        List<Personaje> listaDePersonajes = personajes.getContent();
        List<PersonajeDTO> contenido = listaDePersonajes.stream().map(personaje -> mapearDTO(personaje)).collect(Collectors.toList());

        PersonajeRespuesta personajeRespuesta = new PersonajeRespuesta();
        personajeRespuesta.setContenido(contenido);
        personajeRespuesta.setNumeroPagina(personajes.getNumber());
        personajeRespuesta.setMedidaPagina(personajes.getSize());
        personajeRespuesta.setTotalElementos(personajes.getTotalElements());
        personajeRespuesta.setTotalPaginas(personajes.getTotalPages());
        personajeRespuesta.setUltima(personajes.isLast());

        return personajeRespuesta;
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
        personajeDTO.setHistoria(personaje.getHistoria());

        return personajeDTO;
    }

    //Convierte de DTO a Entidad
    private Personaje mapearEntidad(PersonajeDTO personajeDTO) {
        Personaje personaje = new Personaje();

        personaje.setImagen(personajeDTO.getImagen());
        personaje.setPeso(personajeDTO.getPeso());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setHistoria(personajeDTO.getHistoria());

        return personaje;
    }
}