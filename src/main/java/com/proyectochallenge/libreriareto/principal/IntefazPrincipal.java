package com.proyectochallenge.libreriareto.principal;

import com.proyectochallenge.libreriareto.model.Autor;
import com.proyectochallenge.libreriareto.model.ConsultarAPI;
import com.proyectochallenge.libreriareto.model.Libro;
import com.proyectochallenge.libreriareto.repository.AutoresRepository;
import com.proyectochallenge.libreriareto.repository.LibreriaRepository;
import com.proyectochallenge.libreriareto.service.ConsumoAPI;
import com.proyectochallenge.libreriareto.service.ConvierteDatos;
import com.proyectochallenge.libreriareto.tools.TextoLimiteAceptado;

import java.util.List;
import java.util.Scanner;

public class IntefazPrincipal {

    private Scanner teclado = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibreriaRepository libreriaRepositorio;
    private AutoresRepository autoresRepositorio;

    public IntefazPrincipal(LibreriaRepository libreriaRepositorio, AutoresRepository autoresRepositorio) {
        this.libreriaRepositorio = libreriaRepositorio;
        this.autoresRepositorio = autoresRepositorio;
    }

    public void menuDeAplicacion() {
        System.out.println("\n  [BIENVENIDO A LA APLICACION DE LA LIBRERIA ALURA] ");
        var opcion = -1; //Variable contador para terminar la estructura repetitiva "SWITCH" y el bucle "WHILE"//
        while (opcion != 0) {
            var menu = """
                    \nElija la opción a traves de su número a ingresar:
						1.- Buscar libro por titulo
						2.- Lista libros registrados
						3.- Lista autores registrados
						4.- Lista autores vivos en un determinado año
						5.- Listar libros por idioma
						0 - Cerrar
						""";
            System.out.println(menu);

            try {
                opcion = Integer.parseInt(teclado.nextLine());
                switch (opcion) {
                    case 1:
                        buscarLibroWeb();
                        break;
                    case 2:
                        buscarLibros();
                        break;
                    case 3:
                        buscarAutores();
                        break;
                    case 4:
                        buscarAutoresVivo();
                        break;
                    case 5:
                        buscarPorIdiomas();
                        break;
                    case 0:
                        System.out.println("Gracias por ingresar a nuestra aplicacion.");
                        break;
                    default:
                        System.out.println("Opción inválida, intente denuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor,solo se acepta numeros y no letras en la peticion.");
            }
        }

    }

    private void buscarLibros() {
        List<Libro> libros = libreriaRepositorio.findAll();

        if (!libros.isEmpty()) {
            for (Libro libro : libros) {
                System.out.println("\n\n-------------------------- SOLICITUD DE LIBROS ------------------------\n");
                System.out.println(" Titulo del libro: " + libro.getTitulo());
                System.out.println(" Autor del libro: " + libro.getAutor().getNombre());
                System.out.println(" Lenguaje de origen: " + libro.getLenguaje());
                System.out.println(" Cantidad de descargas: " + libro.getDescargas());
                System.out.println("\n-------------------------------------------------------------------------\n");
            }

        } else {
            System.out.println("\n -------------- LO SENTIMOS PERO NO SE ENCONTRARON RESULTADOS --------------- \n");
        }

    }

    private void buscarAutores() {
        List<Autor> autores = autoresRepositorio.findAll();

        if (!autores.isEmpty()) {
            for (Autor autor : autores) {
                System.out.println("\n\n-------------------------- SOLICITUD DE AUTORES ------------------------\n");
                System.out.println(" Nombre del autor: " + autor.getNombre());
                System.out.println(" Fecha de nacimiento: " + autor.getFechaNacimiento());
                System.out.println(" Fecha de nallecimiento: " + autor.getFechaFallecimiento());
                System.out.println(" Numero de libros publicados: " + autor.getLibros().getTitulo());
                System.out.println("\n-------------------------------------------------------------------------\n");
            }
        } else {
            System.out.println("\n ------------------------ NO SE ENCONTRARON RESULTADOS ---------------------- \n");

        }
    }

    private void buscarAutoresVivo() {
        System.out.println("Ingrese una fecha aleatoria de fallecimiento del autor: ");
        var ahno = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autores = autoresRepositorio.findForYear(ahno);

        if (!autores.isEmpty()) {
            for (Autor autor : autores) {
                System.out.println("\n\n----------------------- CANTIDAD DE AUTORES VIVOS -----------------------\n");
                System.out.println(" Nombre del autor: " + autor.getNombre());
                System.out.println(" Fecha de nacimiento: " + autor.getFechaNacimiento());
                System.out.println(" Fecha de fallecimiento: " + autor.getFechaFallecimiento());
                System.out.println(" Numeros de libros escritos: " + autor.getLibros().getTitulo());
                System.out.println("\n-------------------------------------------------------------------------\n");
            }
        } else {
            System.out.println("\n ------------------------ NO SE ENCONTRARON RESULTADOS ---------------------- \n");

        }
    }

    private void buscarPorIdiomas() {

        var menu = """
				Seleccione un idioma:
					1. Español
					2. Portugués
					3. Catalán
					4. Ingles
					5. Chino
					""";
        System.out.println(menu);
        var idioma = teclado.nextInt();
        teclado.nextLine();

        String seleccion = "";

        switch (idioma){
            case 1:
                seleccion="es";
                break;
            case 2:
                seleccion="pt";
                break;
            case 3:
                seleccion="ca";
                break;
            case 4:
                seleccion="en";
                break;
            case 5:
                seleccion="zh";
                break;
            default:
                System.out.println("Lo sentimos por el momento no hay estos libros\n" +
                        "disponibles en este idioma.\n");
        }
        List<Libro> libros = libreriaRepositorio.findForLanguaje(seleccion);

        if (!libros.isEmpty()) {
            for (Libro libro : libros) {
                System.out.println("\n\n-------------------------- LIBROS POR IDIOMA ------------------------\n");
                System.out.println(" Titulo del libro: " + libro.getTitulo());
                System.out.println(" Autor del libro: " + libro.getAutor().getNombre());
                System.out.println(" Idioma o lenguaje de origen:: " + libro.getLenguaje());
                System.out.println(" Cantidad de descargas: " + libro.getDescargas());
                System.out.println("\n-------------------------------------------------------------------------\n");
            }
        } else {
            System.out.println("\n ------------------------ NO SE ENCONTRARON RESULTADOS ---------------------- \n");
        }
    }

     private void buscarLibroWeb() {
        ConsultarAPI datos = getDatosLibros();
        if (!datos.resultados().isEmpty()) {
            Libro libro = new Libro(datos.resultados().get(0));
            libro = libreriaRepositorio.save(libro);
        }
        System.out.println("\nDatos del libro: ");
        System.out.println("Resultados: " + datos.resultados());
    }

    private ConsultarAPI getDatosLibros() {
        System.out.println("\nEscriba el nombre del libro que desea buscar:\n");
        try {
            var titulo = teclado.nextLine();
            int longitudMaxima=5;
            var definirlimite= TextoLimiteAceptado.longitudMinimaAceptada(titulo,longitudMaxima);
            String buscar="/?search=";
            var LinkBusqueda=URL_BASE+buscar;
            definirlimite = definirlimite.replace(" ", "+");
            System.out.println("\nTitlulo : " + definirlimite);
            System.out.println(LinkBusqueda + definirlimite+"\n");
            var json = consumoApi.obtenerDatos(LinkBusqueda + definirlimite);
            //System.out.println(json);
            ConsultarAPI datos = conversor.obtenerDatos(json, ConsultarAPI.class);
            return datos;
        } catch (Exception e){
            System.out.println("\nPor favor vuelva ingresar de nuevo el titulo " +
                    "\ndel libro, que solo admite 5 a más palabras " +
                    "\npor titulo de libros");
        }
        return getDatosLibros();
    }
}
