package com.mycompany.biblioduoc;

import com.mycompany.biblioduoc.exceptions.LibroYaPrestadoException;
import com.mycompany.biblioduoc.exceptions.LibroNoEncontradoException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.mycompany.biblioduoc.BuscarLibro.BuscarLibro;

public class BiblioDuoc {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n=== Menú Biblioteca Duoc ===");
            System.out.println("1. Listar libros");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Buscar libro");
            System.out.println("4. Prestar libro");
            System.out.println("5. Devolver libro");
            System.out.println("6. Agregar libro");
            System.out.println("7. Agregar usuario");
            System.out.println("8. Cargar libros desde CSV");
            System.out.println("9. Cargar usuarios desde CSV");
            System.out.println("10. Guardar informe de libros");
            System.out.println("11. Guardar informe de usuarios");
            System.out.println("12. Salir");
            System.out.print("Elige una opción: ");

            int opcion = 0;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\nLibros disponibles:");
                    biblioteca.mostrarLibros();
                    break;
                case 2:
                    System.out.println("\nUsuarios registrados:");
                    biblioteca.mostrarUsuarios();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre o autor del libro a buscar: ");
                    String consulta = scanner.nextLine();
                    try {
                        BuscarLibro.buscarPorTituloOAutor(biblioteca.getLibros(), consulta);
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error al buscar el libro: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Libros disponibles para prestar:");
                    boolean hayDisponibles = false;
                    for (Libro libro : biblioteca.getLibros()) {
                        if (!libro.isPrestado()) {
                            System.out.println(libro.getTitulo() + " - " + libro.getAutor());
                            hayDisponibles = true;
                        }
                    }
                    if (!hayDisponibles) {
                        System.out.println("No hay libros disponibles para prestar.");
                        break;
                    }
                    System.out.print("Título del libro a prestar: ");
                    String tituloPrestar = scanner.nextLine();
                    try {
                        biblioteca.prestarLibro(tituloPrestar);
                        System.out.println("Libro prestado con éxito.");
                    } catch (LibroNoEncontradoException | LibroYaPrestadoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Título del libro a devolver: ");
                    String tituloDevolver = scanner.nextLine();
                    try {
                        biblioteca.devolverLibro(tituloDevolver);
                        System.out.println("Libro devuelto con éxito.");
                    } catch (LibroNoEncontradoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.print("Título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor del libro: ");
                    String autor = scanner.nextLine();
                    biblioteca.agregarLibro(new Libro(titulo, autor));
                    System.out.println("Libro agregado.");
                    break;
                case 7:
                    System.out.print("RUT del usuario: ");
                    String rut = scanner.nextLine();
                    System.out.print("Nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    biblioteca.agregarUsuario(new Usuario(rut, nombre));
                    System.out.println("Usuario agregado.");
                    break;
                case 8:
                    System.out.println("Archivos para cargar: libros.csv");
                    System.out.println("Ingrese nombre del archivo: ");
              
                    String archivoLibros = scanner.nextLine();
                    try {
                        biblioteca.cargarLibrosDesdeCSV(archivoLibros);
                        System.out.println("Libros cargados correctamente.");
                    } catch (IOException e) {
                        System.out.println("Error al cargar libros: " + e.getMessage());
                    }
                    break;
                case 9:
                    System.out.println("Archivos para cargar: usuarios.csv ");
                    System.out.println("Ingrese el nombre del archivo: ");
                    String archivoUsuarios = scanner.nextLine();
                    try {
                        biblioteca.cargarUsuariosDesdeCSV(archivoUsuarios);
                        System.out.println("Usuarios cargados correctamente.");
                    } catch (IOException e) {
                        System.out.println("Error al cargar usuarios: " + e.getMessage());
                    }
                    break;
                case 10:
                    System.out.print("Ruta para guardar el informe de libros (ej: informe_libros.txt): ");
                    String informeLibros = scanner.nextLine();
                    try {
                        biblioteca.guardarInformeLibros(informeLibros);
                        System.out.println("Informe de libros guardado correctamente.");
                    } catch (IOException e) {
                        System.out.println("Error al guardar informe: " + e.getMessage());
                    }
                    break;
                case 11:
                    System.out.print("Ruta para guardar el informe de usuarios (ej: informe_usuarios.txt): ");
                    String informeUsuarios = scanner.nextLine();
                    try {
                        biblioteca.guardarInformeUsuarios(informeUsuarios);
                        System.out.println("Informe de usuarios guardado correctamente.");
                    } catch (IOException e) {
                        System.out.println("Error al guardar informe: " + e.getMessage());
                    }
                    break;
                case 12:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
