package com.alura.jdbc.pruebas;

import com.alura.jdbc.Factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class pruebaPool {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i=0; i<20; i++){
            Connection con = connectionFactory.newConnection();
            System.out.println("Abriendo la conexion numero: " + (i + 1));
        }
    }
}
