package com.alkemy.challenge.controladores;

import com.alkemy.challenge.dto.PersonajeDTO;
import com.alkemy.challenge.servicios.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @GetMapping
    public List<PersonajeDTO> listarPersonajes() {
        return personajeServicio.obtenerTodosLosPersonajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> obtenerPersonajePorId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(personajeServicio.obtenerPersonajePorId(id));
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> guardarPersonaje(@RequestBody PersonajeDTO personajeDTO){
        return new ResponseEntity<>(personajeServicio.crearPersonaje(personajeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> actualizarPersonaje(@RequestBody PersonajeDTO personajeDTO,@PathVariable(name = "id") long id){
        PersonajeDTO personajeRespuesta = personajeServicio.actualizarPersonaje(personajeDTO, id);
        return new ResponseEntity<>(personajeRespuesta,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id){
        personajeServicio.eliminarPersonaje(id);
        return new ResponseEntity<>("Personaje eliminado con exito",HttpStatus.OK);
    }
}



















