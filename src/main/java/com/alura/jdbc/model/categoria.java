package com.alura.jdbc.model;

public class Categoria {
    Integer id;
    String nombre;

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return this.nombre;
    }
}
