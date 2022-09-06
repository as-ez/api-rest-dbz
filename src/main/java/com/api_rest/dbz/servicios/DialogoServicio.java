package com.api_rest.dbz.servicios;

import com.api_rest.dbz.dto.DialogoDTO;

import java.util.List;

public interface DialogoServicio {

    public DialogoDTO crearDialogo(long personajeId, DialogoDTO dialogoDTO);

    public List<DialogoDTO> obtenerDialogosPorPersonajeId(long personajeId);

    public DialogoDTO obtenerDialogoPorId(Long personajeId, Long dialogoId);

    public DialogoDTO actualizarDialogo(Long personajeId,Long dialogoId ,DialogoDTO solicitudDeDialogo);

    public void eliminarDialogo(Long personajeId, Long dialogoId);
}
