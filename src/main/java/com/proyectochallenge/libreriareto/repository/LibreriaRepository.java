package com.proyectochallenge.libreriareto.repository;

import com.proyectochallenge.libreriareto.model.Libro;
import java.util.List;

import jakarta.transaction.Transactional;
import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibreriaRepository extends JpaRepository<Libro, Long> {


    // Buscar libros por lenguajes
    @Query("SELECT l FROM Libro l WHERE l.lenguaje >= :idioma")
    List<Libro> findForLanguaje(String idioma);

}
