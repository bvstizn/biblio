package com.mycompany.biblioduoc;

import com.mycompany.biblioduoc.exceptions.LibroYaPrestadoException;

public class Libro {
    private String titulo;
    private String autor;
    private boolean prestado;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isPrestado() { return prestado; }

    public void prestar() throws LibroYaPrestadoException {
        if (prestado) {
            throw new LibroYaPrestadoException("El libro ya está prestado: " + titulo);
        }
        prestado = true;
    }

    public void devolver() {
        prestado = false;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + (prestado ? " (Prestado)" : " (Disponible)");
    }
}