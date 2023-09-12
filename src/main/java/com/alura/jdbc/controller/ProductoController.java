package com.alura.jdbc.controller;

import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.model.Producto;
import com.alura.jdbc.DAO.ProductoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController {
    private ProductoDAO productoDAO;

    public ProductoController() {
        this.productoDAO = new ProductoDAO(new ConnectionFactory().newConnection());
    }

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
        Connection con = new ConnectionFactory().newConnection();
        int updateCount;

        String sql = "UPDATE product SET name=?, description=?, amount=? WHERE id=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, descripcion);
            preparedStatement.setInt(3, cantidad);
            preparedStatement.setInt(4, id);

            updateCount = preparedStatement.executeUpdate();
        }

        con.close();
        return updateCount;
    }

    public int eliminar(Integer id) {

        return productoDAO.eliminar(id);
    }

    public List<Producto> listar() {
        return productoDAO.listar();
    }

    public void guardar(Producto producto){
        productoDAO.guardar(producto);
    }



}
