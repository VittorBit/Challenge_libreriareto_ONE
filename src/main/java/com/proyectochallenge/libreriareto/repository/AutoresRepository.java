package com.proyectochallenge.libreriareto.repository;

import com.proyectochallenge.libreriareto.model.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AutoresRepository extends JpaRepository<Autor, Long> {
    // Buscar autores vivos. //
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :ahno AND a.fechaFallecimiento > :ahno")
    List<Autor> findForYear(int ahno);
}
