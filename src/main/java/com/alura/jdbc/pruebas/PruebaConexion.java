package com.alura.jdbc.pruebas;

import com.alura.jdbc.Factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().newConnection();
        System.out.println("Cerrando la conexión");

        con.close();
    }

}
