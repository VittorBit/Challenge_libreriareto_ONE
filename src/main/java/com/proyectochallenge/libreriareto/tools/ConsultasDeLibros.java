package com.proyectochallenge.libreriareto.tools;

import com.proyectochallenge.libreriareto.model.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ConsultasDeLibros {
    /**
     * Este metodo solo es una prueba de como funciona las inserciones en spring boot de java
     * ya que nos sirve de ejemplo de consultar insercion por este framework.
     */
    // Buscar por la lista de libros //

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Libro insertWithQuery(Libro libros) {
        entityManager.createNativeQuery("INSERT INTO libros (id, titulo,lenguaje,descargas) VALUES (?,?,?)")
                .setParameter(1, libros.getId())
                .setParameter(2, libros.getTitulo())
                .setParameter(3, libros.getLenguaje())
                .setParameter(4, libros.getDescargas())
                .executeUpdate();
        return libros;
    }
}
