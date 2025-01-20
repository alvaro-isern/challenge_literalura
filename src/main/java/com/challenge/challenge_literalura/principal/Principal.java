package com.challenge.challenge_literalura.principal;

import com.challenge.challenge_literalura.model.*;
import com.challenge.challenge_literalura.repository.AutorRepositorio;
import com.challenge.challenge_literalura.repository.LibroRepositorio;
import com.challenge.challenge_literalura.service.ConexionAPI;
import com.challenge.challenge_literalura.service.ConvertirJson;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConexionAPI consultaAPI = new ConexionAPI();
    Scanner sc = new Scanner(System.in);
    ConvertirJson convertir = new ConvertirJson();
    LibroRepositorio libroRepository;
    AutorRepositorio autorRepository;
    Libro libro;

    public Principal() {
    }

    public Principal(LibroRepositorio libroRepositorio, AutorRepositorio autorRepositorio) {
        this.libroRepository = libroRepositorio;
        this.autorRepository = autorRepositorio;
    }

    public void menuDeOpciones() {

        boolean salir = false;
        while (!salir) {
            System.out.println("""
                    =======================================================
                    *************** SELECCIONE UNA OPCIÓN *****************
                    =======================================================
                        1) Buscar por Título de libro
                        2) Listar todos los libros registrados
                        3) Listar autores registrados
                        4) Listar autores vivos por un determinado año
                        5) Listar libros por idioma                           
                        0) Salir
                    =======================================================
                    """);
            try {
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> buscarPorTituloDeLibro();
                    case 2 -> listarLibrosRegistrados();
                    case 3 -> listarAutoresRegistrados();
                    case 4 -> listarAutoresVivosPorFecha();
                    case 5 -> listarLibrosPorIdioma();
                    case 0 -> {
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                    }
                    default -> System.out.println("Ingrese una opción válida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido");
                sc.nextLine();
            }
        }
    }

    private DatosLibros buscarLibroApi() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var nombreLibro = sc.nextLine();
        var json = consultaAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        Datos datos = convertir.leerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            return libroBuscado.get();
        }
        return null;
    }


    private void buscarPorTituloDeLibro() {
        try {
            DatosLibros datos = buscarLibroApi();

            libro = new Libro(datos);
            libroRepository.save(libro);
            System.out.print("""
                    <<<--------------------------(*)-------------------------->>>
                    (*)                    LIBRO ENCONTRADO                   (*)
                    <<<--------------------------(*)-------------------------->>>
                    """);
            System.out.println(libro);
        } catch (DataIntegrityViolationException e) {
            System.out.println("El libro ya se encuentra registrado en el sistema.");
        } catch (NullPointerException e) {
            System.out.println("Libro no encontrado, Intente nuevamente");
        } catch (Exception e) {
            System.out.println("Error al guardar el libro: " + e.getMessage());
        }
    }

    private Libro listarLibrosRegistrados() {
        if (libroRepository.count() == 0) {
            System.out.println("""
                    ////////////////////////////////////////
                    No hay libros registrados en el sistema
                    ////////////////////////////////////////
                    """);
            return null;
        }
        System.out.println("""
                =============================================================
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                               LISTADO DE LIBROS REGISTRADOS
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                =============================================================
                """);
        libroRepository.findAll().forEach(System.out::println);
        return null;
    }

    private void listarAutoresRegistrados() {
        if (autorRepository.count() == 0) {
            System.out.println("""
                    ////////////////////////////////////////
                    No hay autores registrados en el sistema
                    ////////////////////////////////////////
                    """);
            return;
        }
        System.out.println("""
                =============================================================
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                               LISTADO DE AUTORES REGISTRADOS
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                =============================================================
                """);
        libroRepository.findAll().forEach(l -> System.out.println(l.getAutor()));
    }

    private void listarAutoresVivosPorFecha() {
        if (autorRepository.count() == 0) {
            System.out.println("""
                    ////////////////////////////////////////
                    No hay autores registrados en el sistema
                    ////////////////////////////////////////
                    """);
            return;
        }
        System.out.println("Ingrese el año para buscar autores vivos: ");
        int anio = sc.nextInt();

        List<Autor> autores = autorRepository.findAutoresVivosPorAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("""
                ////////////////////////////////////////////////////////
                No hay autores vivos en el sistema para el año ingresado
                ////////////////////////////////////////////////////////
                    """);
        } else {
            System.out.println("""
                =============================================================
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                   LISTADO DE AUTORES VIVOS
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                =============================================================
                """);
            autores.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma() {
        if (libroRepository.count() == 0) {
            System.out.println("""
                    ////////////////////////////////////////
                    No hay libros registrados en el sistema
                    ////////////////////////////////////////
                    """);
            return;
        }
        System.out.println("""
                /,,,,,,,,,,,,,,,,,,,,,,,,/
                |   CODIGO DE IDIOMAS   |
                |'''''''''''''''''''''''|
                |    en ---> ingles     |
                |    es ---> español    |
                /''''''''''''''''''''''''/
                """);
        String idioma = "";
        int contador = 0;
        System.out.println("-------Recuerde que tiene 3 intentos-------");
        do {
            System.out.println("Intento: " + (contador + 1) + "\n");
            System.out.println("Ingrese el idioma del libro que desea buscar: ");
            idioma = sc.nextLine();
            if (idioma.isBlank()) {
                System.out.print("""
                        ////////////////////////////////////////
                            El idioma no puede estar vacío
                        ////////////////////////////////////////
                        """);
            }
            contador++;
        } while (idioma.isBlank() && contador < 3);

        if (contador == 3 && idioma.isBlank()) {
            System.out.println("""
                ////////////////////////////////////////
                    Ha superado el número de intentos
                ////////////////////////////////////////
                    """);
            return;
        }

        List<Libro> libros = libroRepository.findByIdiomasContainingIgnoreCase(idioma);

        if (libros.isEmpty()) {
            System.out.println("""
                ////////////////////////////////////////////////////
                No hay libros en el sistema para el idioma ingresado
                ////////////////////////////////////////////////////
                    """);
        } else {
            System.out.println("""
                =============================================================
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                   LISTADO DE LIBROS POR IDIOMA
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<(*)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                =============================================================
                """);
            libros.forEach(System.out::println);
        }
    }

}
