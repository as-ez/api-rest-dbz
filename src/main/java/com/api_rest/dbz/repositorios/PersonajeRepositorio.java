package com.api_rest.dbz.repositorios;

import com.api_rest.dbz.modelos.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonajeRepositorio extends JpaRepository<Personaje, Long> {
}
