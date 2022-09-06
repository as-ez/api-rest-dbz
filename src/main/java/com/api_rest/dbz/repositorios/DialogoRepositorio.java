package com.api_rest.dbz.repositorios;

import com.api_rest.dbz.modelos.Dialogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DialogoRepositorio extends JpaRepository<Dialogo, Long> {

    public List<Dialogo> findByPersonajeId(long personajeId);
}
