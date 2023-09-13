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
    public void guardar(Producto producto) {

        try {
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO PRODUCTO "
                            + "(nombre, descripcion, cantidad)"
                            + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                statement.setString(1, producto.getNombre());
                statement.setString(2, producto.getDescripcion());
                statement.setInt(3, producto.getCantidad());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        producto.setId(resultSet.getInt(1));

                        System.out.println(String.format("Fue insertado el producto: %s", producto));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Producto> listar() {
        List<Producto> resultado = new ArrayList<>();
        // ConnectionFactory factory = new ConnectionFactory();
        //final Connection con = new ConnectionFactory().newConnection();
        try  {
            PreparedStatement statement = con.prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto");
            statement.execute();
            final ResultSet resultSet = statement.getResultSet();
            try(resultSet){
                while (resultSet.next()) {
                    Producto fila = new Producto(resultSet.getInt("id"),
                            resultSet.getString("nombre"), resultSet.getString("descripcion"),
                            resultSet.getInt("cantidad"));
                    resultado.add(fila);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
    public int eliminar(Integer id) {

        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM producto WHERE id = ?");
            try(statement) {

                statement.setInt(1, id);
                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id){

        int updateCount;
        String sql = "UPDATE producto SET nombre=?, descripcion=?, cantidad=? WHERE id=?";
        try{
            final PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (preparedStatement) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, descripcion);
                preparedStatement.setInt(3, cantidad);
                preparedStatement.setInt(4, id);

                updateCount = preparedStatement.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updateCount;
    }

}