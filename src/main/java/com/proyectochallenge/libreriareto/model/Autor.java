package com.proyectochallenge.libreriareto.model;

import com.proyectochallenge.libreriareto.tools.LimitarCaracteres;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    @OneToOne
    @JoinTable(
            name = "Libro",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Libro libros;

    public Autor(){}

    public Autor(DatosAutor autor) {
        this.nombre = LimitarCaracteres.limitarLongitud(autor.nombre(), 200);

        if (autor.fechaDeNacimiento() == null)
            this.fechaNacimiento = 1980;
        else
            this.fechaNacimiento = autor.fechaDeNacimiento();

        if (autor.fechaDeMuerte() == null)
            this.fechaFallecimiento = 3022;
        else
            this.fechaFallecimiento = autor.fechaDeMuerte();
    }

    @Override
    public String toString() {
        return "Autores [id=" + id + ", nombre=" + nombre + ", fechaNacimiento="
                + fechaNacimiento + ", fechaFallecimiento=" + fechaFallecimiento
                + ", libro="  + libros +"]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public Libro getLibros() {
        return libros;
    }

    public void setLibros(Libro libros) {
        this.libros = libros;
    }
}
