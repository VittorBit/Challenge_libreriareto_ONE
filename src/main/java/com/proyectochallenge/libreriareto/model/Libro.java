package com.proyectochallenge.libreriareto.model;


import com.proyectochallenge.libreriareto.tools.LimitarCaracteres;
import jakarta.persistence.*;

@Entity
@Table(name = "Libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String lenguaje;
    private Integer descargas;
    @OneToOne(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;

    public Libro() { }

    public Libro( DatosLibro libro) {
        this.id =libro.id();
        this.titulo = LimitarCaracteres.limitarLongitud(libro.titulo(), 200);
        this.descargas = libro.descargas();
        if (!libro.lenguajes().isEmpty())
            this.lenguaje = libro.lenguajes().get(0);
        if (!libro.autores().isEmpty()) {
            for (DatosAutor autor : libro.autores()) {
                this.autor = new Autor(autor);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", lenguaje='" + lenguaje + '\'' +
                ", descargas=" + descargas +
                ", autor=" + autor +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
