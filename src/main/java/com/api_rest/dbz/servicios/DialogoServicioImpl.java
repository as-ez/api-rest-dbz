package com.api_rest.dbz.servicios;

import com.api_rest.dbz.dto.DialogoDTO;
import com.api_rest.dbz.excepciones.DbzAppException;
import com.api_rest.dbz.excepciones.ResourceNotFoundException;
import com.api_rest.dbz.modelos.Dialogo;
import com.api_rest.dbz.modelos.Personaje;
import com.api_rest.dbz.repositorios.DialogoRepositorio;
import com.api_rest.dbz.repositorios.PersonajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DialogoServicioImpl implements DialogoServicio{

    @Autowired
    private DialogoRepositorio dialogoRepositorio;

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Override
    public DialogoDTO crearDialogo(long personajeId, DialogoDTO dialogoDTO) {
        Dialogo dialogo = mapearEntidad(dialogoDTO);
        Personaje personaje = personajeRepositorio.findById(personajeId)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", personajeId));

        dialogo.setPersonaje(personaje);
        Dialogo nuevoDialogo = dialogoRepositorio.save(dialogo);
        return mapearDTO(nuevoDialogo);
    }

    @Override
    public List<DialogoDTO> obtenerDialogosPorPersonajeId(long personajeId) {
        List<Dialogo> dialogos = dialogoRepositorio.findByPersonajeId(personajeId);
        return dialogos.stream().map(dialogo -> mapearDTO(dialogo)).collect(Collectors.toList());
    }

    @Override
    public DialogoDTO obtenerDialogoPorId(Long personajeId, Long dialogoId) {
        Personaje personaje = personajeRepositorio.findById(personajeId)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", personajeId));

        Dialogo dialogo = dialogoRepositorio.findById(dialogoId)
                .orElseThrow(() -> new ResourceNotFoundException("Dialogo", "id", dialogoId));

        if(!dialogo.getPersonaje().getId().equals(personaje.getId())) {
            throw  new DbzAppException(HttpStatus.BAD_REQUEST, "El dialogo no pertenece al personaje");
        }

        return  mapearDTO(dialogo);
    }

    @Override
    public DialogoDTO actualizarDialogo(Long personajeId,Long dialogoId ,DialogoDTO solicitudDeDialogo) {
        Personaje personaje = personajeRepositorio.findById(personajeId)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", personajeId));

        Dialogo dialogo = dialogoRepositorio.findById(dialogoId)
                .orElseThrow(() -> new ResourceNotFoundException("Dialogo", "id", dialogoId));

        if(!dialogo.getPersonaje().getId().equals(personaje.getId())) {
            throw  new DbzAppException(HttpStatus.BAD_REQUEST, "El dialogo no pertenece al personaje");
        }

        dialogo.setNombre(solicitudDeDialogo.getNombre());
        dialogo.setDescripcion(solicitudDeDialogo.getDescripcion());
        dialogo.setAudio(solicitudDeDialogo.getAudio());

        Dialogo dialogoActualizado = dialogoRepositorio.save(dialogo);
        return mapearDTO(dialogoActualizado);
    }

    @Override
    public void eliminarDialogo(Long personajeId, Long dialogoId) {

        Personaje personaje = personajeRepositorio.findById(personajeId)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", personajeId));

        Dialogo dialogo = dialogoRepositorio.findById(dialogoId)
                .orElseThrow(() -> new ResourceNotFoundException("Dialogo", "id", dialogoId));

        if(!dialogo.getPersonaje().getId().equals(personaje.getId())) {
            throw  new DbzAppException(HttpStatus.BAD_REQUEST, "El dialogo no pertenece al personaje");
        }

        dialogoRepositorio.delete(dialogo);
    }

    private DialogoDTO mapearDTO(Dialogo dialogo) {
        DialogoDTO dialogoDTO = new DialogoDTO();
        dialogoDTO.setId(dialogo.getId());
        dialogoDTO.setNombre(dialogo.getNombre());
        dialogoDTO.setDescripcion(dialogo.getDescripcion());
        dialogoDTO.setAudio(dialogo.getAudio());

        return dialogoDTO;
    }

    private Dialogo mapearEntidad(DialogoDTO dialogoDTO){
        Dialogo dialogo = new Dialogo();
        dialogo.setId(dialogoDTO.getId());
        dialogo.setNombre(dialogoDTO.getNombre());
        dialogo.setDescripcion(dialogoDTO.getDescripcion());
        dialogo.setAudio(dialogoDTO.getAudio());

        return dialogo;
    }
}
