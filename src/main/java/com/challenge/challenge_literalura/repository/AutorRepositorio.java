package com.challenge.challenge_literalura.repository;

import com.challenge.challenge_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE :anio BETWEEN a.fechaNacimiento AND COALESCE(a.fechaFallecimiento-1, 2024)")
    List<Autor> findAutoresVivosPorAnio(int anio);
}
