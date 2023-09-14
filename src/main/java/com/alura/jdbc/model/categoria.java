package com.alura.jdbc.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    Integer id;
    String nombre;
    private List<Producto> productos = new ArrayList<>();

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Integer getId() {
        return this.id;
    }
    @Override
    public String toString() {
        return this.nombre;
    }

    public void agregar(Producto producto) {
        this.productos.add(producto);
    }

    public List<Producto> getProductos() {
        return this.productos;
    }
}
