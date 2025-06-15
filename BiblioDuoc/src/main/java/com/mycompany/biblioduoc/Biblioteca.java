package com.mycompany.biblioduoc;
import com.mycompany.biblioduoc.exceptions.LibroYaPrestadoException;
import com.mycompany.biblioduoc.exceptions.LibroNoEncontradoException;
import java.io.*;
import java.util.*;

public class Biblioteca {
    private ArrayList<Libro> libros;
    private HashMap<String, Usuario> usuarios;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new HashMap<>();
    }

    // Agrega un libro
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    // Agrega un usuario
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getRut(), usuario);
    }

    // Busca un libro por título
    public Libro buscarLibro(String titulo) throws LibroNoEncontradoException {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        throw new LibroNoEncontradoException("El libro '" + titulo + "' no existe en la biblioteca.");
    }

    // Presta un libro
    public void prestarLibro(String titulo) throws LibroNoEncontradoException, LibroYaPrestadoException {
        Libro libro = buscarLibro(titulo);
        if (libro.isPrestado()) {
            throw new LibroYaPrestadoException("El libro '" + titulo + "' ya está prestado.");
        }
        libro.prestar();
    }

    // Devuelve un libro
    public void devolverLibro(String titulo) throws LibroNoEncontradoException {
        Libro libro = buscarLibro(titulo);
        libro.devolver();
    }

    // Carga libros desde un archivo CSV
    public void cargarLibrosDesdeCSV(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    libros.add(new Libro(partes[0].trim(), partes[1].trim()));
                }
            }
        }
    }

    // Carga usuarios desde un archivo CSV
    public void cargarUsuariosDesdeCSV(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    usuarios.put(partes[0].trim(), new Usuario(partes[0].trim(), partes[1].trim()));
                }
            }
        }
    }

    // Guarda informe de libros en texto
    public void guardarInformeLibros(String rutaArchivo) throws IOException {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            for (Libro libro : libros) {
                writer.write(libro.toString() + "\n");
            }
        }
    }

    // Guarda informe de usuarios en texto
    public void guardarInformeUsuarios(String rutaArchivo) throws IOException {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            for (Usuario usuario : usuarios.values()) {
                writer.write(usuario.toString() + "\n");
            }
        }
    }

    // Muestra todos los libros
    public void mostrarLibros() {
    if (libros.isEmpty()) {
        System.out.println("No hay libros registrados en la biblioteca.");
        return;
    }
    for (Libro libro : libros) {
        System.out.println(libro);
    }
}

    // Muestra todos los usuarios
    public void mostrarUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            System.out.println(usuario);
        }
    }
}