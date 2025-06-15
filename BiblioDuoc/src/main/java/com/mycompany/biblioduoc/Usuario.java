/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioduoc;

import com.mycompany.biblioduoc.exceptions.LibroNoEncontradoException;

import com.mycompany.biblioduoc.exceptions.LibroYaPrestadoException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Usuario {
    private String rut;
    private String nombre;

    public Usuario(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (" + rut + ")";
    }
}