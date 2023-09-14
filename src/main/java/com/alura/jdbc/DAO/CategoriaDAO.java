package com.alura.jdbc.DAO;

import com.alura.jdbc.model.Categoria;
import com.alura.jdbc.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    final private Connection con;

    public CategoriaDAO(Connection con) {
        this.con = con;
    }

    public List<Categoria> listar() {
        List<Categoria> resultado = new ArrayList<>();
        var selectQuery = "SELECT id, nombre FROM categoria";
        System.out.println(selectQuery);
        try {
            PreparedStatement statement = con.prepareStatement
                    (selectQuery);
           final ResultSet resultSet = statement.executeQuery();
           try (resultSet){
                while (resultSet.next()){
                    var categoria = new Categoria(resultSet.getInt("id"),
                            resultSet.getString("nombre"));
                    resultado.add(categoria);
                }
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public List<Categoria> listarConProductos() {
        List<Categoria> resultado = new ArrayList<>();
        String selectQuery = "SELECT C.ID, C.NOMBRE, P.ID, P.NOMBRE, P.CANTIDAD "
                + " FROM CATEGORIA C INNER JOIN PRODUCTO P "
                + " ON C.ID = P.CATEGORIA_ID";
        System.out.println(selectQuery);
        try {
            PreparedStatement statement = con.prepareStatement
                    (selectQuery);
            final ResultSet resultSet = statement.executeQuery();
            try (resultSet){
                while (resultSet.next()){
                    Integer categoriaId =  resultSet.getInt("c.id");
                    String categoriaNombre =  resultSet.getString("c.nombre");
                    var categoria = resultado.stream()
                            .filter(cat -> cat.getId().equals(categoriaId))
                            .findAny().orElseGet(() -> {
                                Categoria cat = new Categoria(categoriaId, categoriaNombre);resultado.add(cat);
                                return cat;
                            });
                    Producto producto = new Producto(
                            resultSet.getInt("P.ID"),
                            resultSet.getString("P.NOMBRE"),
                            resultSet.getInt("P.CANTIDAD"));

                    categoria.agregar(producto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
