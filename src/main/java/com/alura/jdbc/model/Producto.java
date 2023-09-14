package com.alura.jdbc.model;

public class Producto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Integer categoriaId;

    public Producto(String nombre, String descripcion, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Producto(int id, String nombre, String description, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = description;
        this.cantidad = cantidad;

    }

    public Producto(int id, String nombre, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Object getId() {return id;}
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }


    @Override
    public String toString() {
        return String.format("{id: %s, nombre: %s, descripcion: %s, cantidad: %d}",
                this.id, this.nombre, this.descripcion, this.cantidad);
    }


    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;

    }

    public int getIdCategoria() {
        return this.categoriaId;
    }
}

