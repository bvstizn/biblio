package com.mycompany.biblioduoc.BuscarLibro;

import com.mycompany.biblioduoc.Libro;
import java.util.List;

public class BuscarLibro {

    /**
     * Busca libros por coincidencias en el título (no distingue
     * mayúsculas/minúsculas).
     *
     * @param libros Lista de libros a buscar
     * @param consulta Texto a buscar (parte o todo del título)
     */
    public static void buscarPorTitulo(List<Libro> libros, String consulta) {
        boolean encontrado = false;
        System.out.println("Resultados de búsqueda por título para: \"" + consulta + "\"");
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(consulta.toLowerCase())) {
                System.out.println(libro.getTitulo() + " - " + libro.getAutor()
                        + (libro.isPrestado() ? " (Prestado)" : " (Disponible)"));
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros que coincidan con la búsqueda.");
        }
    }

    /**
     * Busca libros por coincidencias en el autor (no distingue
     * mayúsculas/minúsculas).
     *
     * @param libros Lista de libros a buscar
     * @param consulta Texto a buscar (parte o todo del nombre del autor)
     */
    public static void buscarPorTituloOAutor(List<Libro> libros, String consulta) {
        boolean encontrado = false;
        String consultaLower = consulta.toLowerCase();
        System.out.println("Resultados de búsqueda para: \"" + consulta + "\"");
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(consultaLower)
                    || libro.getAutor().toLowerCase().contains(consultaLower)) {
                System.out.println(libro.getTitulo() + " - " + libro.getAutor()
                        + (libro.isPrestado() ? " (Prestado)" : " (Disponible)"));
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros que coincidan con la búsqueda.");
        }
    }
}
