package com.alkemy.challenge.repositorios;

import com.alkemy.challenge.modelos.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonajeRepositorio extends JpaRepository<Personaje, Long> {
}
