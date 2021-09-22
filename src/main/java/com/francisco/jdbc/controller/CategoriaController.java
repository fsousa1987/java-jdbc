package com.francisco.jdbc.controller;

import com.francisco.jdbc.dao.CategoriaDAO;
import com.francisco.jdbc.factory.ConnectionFactory;
import com.francisco.jdbc.modelo.Categoria;

import java.util.List;

public class CategoriaController {

    private final CategoriaDAO categoriaDAO;

    public CategoriaController() {
        var connection = new ConnectionFactory().recuperarConexao();

        this.categoriaDAO = new CategoriaDAO(connection);
    }

    public List<Categoria> listar() {
        return this.categoriaDAO.listar();
    }
}
