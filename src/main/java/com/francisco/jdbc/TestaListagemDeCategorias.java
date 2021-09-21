package com.francisco.jdbc;

import com.francisco.jdbc.dao.CategoriaDAO;
import com.francisco.jdbc.factory.ConnectionFactory;

import java.sql.SQLException;

public class TestaListagemDeCategorias {

    public static void main(String[] args) throws SQLException {

        try (var connection = new ConnectionFactory().recuperarConexao()) {
            var categoriaDAO = new CategoriaDAO(connection);
            var listaDeCategorias = categoriaDAO.listar();
            listaDeCategorias.forEach(categoria -> System.out.println(categoria.getNome()));
        }
    }
}
