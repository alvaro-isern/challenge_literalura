package com.challenge.challenge_literalura.repository;

import com.challenge.challenge_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    List<Libro> findByIdiomasContainingIgnoreCase(String idioma);
}
