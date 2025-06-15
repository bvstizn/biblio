package com.mycompany.biblioduoc;

import com.mycompany.biblioduoc.exceptions.LibroYaPrestadoException;
import com.mycompany.biblioduoc.exceptions.LibroNoEncontradoException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BiblioDuoc {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        // Carga inicial de libros y usuarios desde archivos (opcional)
        try {
            biblioteca.cargarLibrosDesdeCSV("libros.csv");
            biblioteca.cargarUsuariosDesdeCSV("usuarios.csv");
            System.out.println("Libros y usuarios cargados desde CSV.");
        } catch (IOException e) {
            System.out.println("No se pudo cargar desde archivos CSV: " + e.getMessage());
        }

        int opcion = 0;

        do {
            System.out.println("\n--- Menú Biblioteca DUOC ---");
            System.out.println("1. Buscar libro");
            System.out.println("2. Prestar libro");
            System.out.println("3. Devolver libro");
            System.out.println("4. Mostrar libros");
            System.out.println("5. Mostrar usuarios");
            System.out.println("6. Agregar libro");
            System.out.println("7. Agregar usuario");
            System.out.println("8. Guardar informe de libros");
            System.out.println("9. Guardar informe de usuarios");
            System.out.println("10. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        System.out.print("Título del libro: ");
                        String tituloBuscar = scanner.nextLine();
                        try {
                            Libro libro = biblioteca.buscarLibro(tituloBuscar);
                            System.out.println("Libro encontrado: " + libro);
                        } catch (LibroNoEncontradoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Título del libro a prestar: ");
                        String tituloPrestar = scanner.nextLine();
                        try {
                            biblioteca.prestarLibro(tituloPrestar);
                            System.out.println("Libro prestado exitosamente.");
                        } catch (LibroNoEncontradoException | LibroYaPrestadoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Título del libro a devolver: ");
                        String tituloDevolver = scanner.nextLine();
                        try {
                            biblioteca.devolverLibro(tituloDevolver);
                            System.out.println("Libro devuelto.");
                        } catch (LibroNoEncontradoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        biblioteca.mostrarLibros();
                        break;

                    case 5:
                        biblioteca.mostrarUsuarios();
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
                        System.out.print("ID del usuario: ");
                        String id = scanner.nextLine();
                        System.out.print("Nombre del usuario: ");
                        String nombre = scanner.nextLine();
                        biblioteca.agregarUsuario(new Usuario(id, nombre));
                        System.out.println("Usuario agregado.");
                        break;

                    case 8:
                        System.out.print("Ruta del archivo para el informe de libros (ej: informe_libros.txt): ");
                        String archivoLibros = scanner.nextLine();
                        try {
                            biblioteca.guardarInformeLibros(archivoLibros);
                            System.out.println("Informe de libros guardado correctamente.");
                        } catch (IOException e) {
                            System.out.println("Error al guardar informe: " + e.getMessage());
                        }
                        break;

                    case 9:
                        System.out.print("Ruta del archivo para el informe de usuarios (ej: informe_usuarios.txt): ");
                        String archivoUsuarios = scanner.nextLine();
                        try {
                            biblioteca.guardarInformeUsuarios(archivoUsuarios);
                            System.out.println("Informe de usuarios guardado correctamente.");
                        } catch (IOException e) {
                            System.out.println("Error al guardar informe: " + e.getMessage());
                        }
                        break;

                    case 10:
                        System.out.println("¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.nextLine(); // Limpiar buffer
            }
        } while (opcion != 10);

        scanner.close();
    }
}