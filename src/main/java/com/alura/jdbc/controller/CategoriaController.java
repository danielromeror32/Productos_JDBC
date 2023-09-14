package com.alura.jdbc.controller;

import com.alura.jdbc.DAO.CategoriaDAO;
import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {
    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        var factory = new ConnectionFactory();

        this.categoriaDAO = new CategoriaDAO(factory.newConnection());
    }

    public List<Categoria> listar() {
		return categoriaDAO.listar();
	}

    public List<Categoria> cargaReporte() {
        // TODO
        return this.categoriaDAO.listarConProductos();
    }

}
