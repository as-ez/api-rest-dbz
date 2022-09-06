package com.api_rest.dbz.controladores;

import com.api_rest.dbz.dto.DialogoDTO;
import com.api_rest.dbz.servicios.DialogoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class DialogoControlador {

    @Autowired
    private DialogoServicio dialogoServicio;

    @GetMapping("/personajes/{personajeId}/dialogos")
    public List<DialogoDTO> listarDialogosPorPublicacionId(
            @PathVariable(value = "personajeId") Long personajeId){
        return dialogoServicio.obtenerDialogosPorPersonajeId(personajeId);
    }

    @GetMapping("/personajes/{personajeId}/dialogos/{id}")
    public ResponseEntity<DialogoDTO> obtenerDialogoPorId(
            @PathVariable(value = "personajeId") Long personajeId,
            @PathVariable(value = "id") Long dialogoId) {
        DialogoDTO dialogoDTO = dialogoServicio.obtenerDialogoPorId(personajeId, dialogoId);
        return  new ResponseEntity<>(dialogoDTO,HttpStatus.OK);
    }

    @PostMapping("/personajes/{personajeId}/dialogos")
    public ResponseEntity<DialogoDTO> guardarDialogo (
            @PathVariable(value = "personajeId") long personajeId,
            @RequestBody DialogoDTO dialogoDTO) {
        return new ResponseEntity<>(dialogoServicio.crearDialogo(personajeId,dialogoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/personajes/{personajeId}/dialogos/{id}")
    public ResponseEntity<DialogoDTO> actualizarDialogo(
            @PathVariable(value = "personajeId") Long personajeId,
            @PathVariable(value = "id") Long dialogoId,
            @RequestBody DialogoDTO dialogoDTO) {
        DialogoDTO dialogoActualizado = dialogoServicio.actualizarDialogo(personajeId,dialogoId,dialogoDTO);
        return new ResponseEntity<>(dialogoActualizado,HttpStatus.OK);
    }

    @DeleteMapping("/personajes/{personajeId}/dialogos/{id}")
    public ResponseEntity<String> eliminarDialogo(
            @PathVariable(value = "personajeId") Long personajeId,
            @PathVariable(value = "id") Long dialogoId){
        dialogoServicio.eliminarDialogo(personajeId,dialogoId);
        return new ResponseEntity<>("Dialogo eliminado con extio",HttpStatus.OK);
    }

}




