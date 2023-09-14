package com.alura.jdbc.controller;

import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.model.Categoria;
import com.alura.jdbc.model.Producto;
import com.alura.jdbc.DAO.ProductoDAO;

import java.util.List;

public class ProductoController {
    private ProductoDAO productoDAO;

    public ProductoController() {
        this.productoDAO = new ProductoDAO(new ConnectionFactory().newConnection());
    }

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {

        return productoDAO.modificar(nombre, descripcion, cantidad, id);
    }

    public int eliminar(Integer id) {

        return productoDAO.eliminar(id);
    }

    public List<Producto> listar() {
        return productoDAO.listar();
    }
    public List<Producto> listar(Categoria categoria){
        return productoDAO.listar(categoria.getId());
    }

    public void guardar(Producto producto, Integer categoriaId) {
        producto.setCategoriaId(categoriaId);
        productoDAO.guardar(producto);
    }


}
