package com.alura.jdbc.DAO;

import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoDAO {
    final private Connection con;

    public ProductoDAO(Connection con) {
        this.con = con;
    }

    public List<Producto> listar(){
        List<Producto> resultado = new ArrayList<>();
        // ConnectionFactory factory = new ConnectionFactory();
        //final Connection con = new ConnectionFactory().newConnection();
        try (con) {
            PreparedStatement statement = con.prepareStatement("SELECT id, name, description, amount FROM product");
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Producto fila = new Producto(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString("description"),
                        resultSet.getInt("amount"));
                resultado.add(fila);
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void guardar(Producto producto) {
        try (con) {
            ejecutarRegistro(producto, con);
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private static void ejecutarRegistro(Producto producto, Connection con) {
        try (PreparedStatement statement = con.prepareStatement("INSERT INTO product (name, description, amount) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setInt(3, producto.getCantidad());
            statement.execute();
            final ResultSet resultSet = statement.getGeneratedKeys();
            try (resultSet) {
                while (resultSet.next()) {
                    producto.setId(resultSet.getInt(1));
                    System.out.println(
                            String.format(
                                    "Fue insertado el producto ID %s", producto));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}