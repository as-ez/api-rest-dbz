package com.api_rest.dbz.controladores;

import com.api_rest.dbz.dto.PersonajeDTO;
import com.api_rest.dbz.dto.PersonajeRespuesta;
import com.api_rest.dbz.servicios.PersonajeServicio;
import com.api_rest.dbz.utilerias.AppConstantes;
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
    public PersonajeRespuesta listarPersonajes(
            @RequestParam(value = "pageNro", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir)  {
        return personajeServicio.obtenerTodosLosPersonajes(numeroDePagina,medidaDePagina,ordenarPor,sortDir);
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



















