/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.biblioduoc;

import java.util.Scanner;

public class BiblioDuoc {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        // Ejemplo: agregar datos iniciales
        biblioteca.agregarLibro(new Libro("El Principito", "Antoine de Saint-Exupéry"));
        biblioteca.agregarUsuario(new Usuario("12345678-9", "Juan Pérez"));

        boolean salir = false;

        while (!salir) {
            System.out.println("=== Menú Biblioteca Duoc ===");
            System.out.println("1. Listar libros");
            System.out.println("2. Prestar libro");
            System.out.println("3. Devolver libro");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            try {
                switch (opcion) {
                    case 1:
                        System.out.println("Libros:");
                        for (Libro libro : biblioteca.libros) {
                            System.out.println(libro);
                        }
                        break;
                    case 2:
                        System.out.print("Título del libro a prestar: ");
                        String tituloPrestar = scanner.nextLine();
                        biblioteca.prestarLibro(tituloPrestar);
                        System.out.println("Libro prestado con éxito.");
                        break;
                    case 3:
                        System.out.print("Título del libro a devolver: ");
                        String tituloDevolver = scanner.nextLine();
                        biblioteca.devolverLibro(tituloDevolver);
                        System.out.println("Libro devuelto con éxito.");
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (LibroNoEncontradoException | LibroYaPrestadoException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }

        scanner.close();
    }
}