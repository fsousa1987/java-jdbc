package com.francisco.jdbc;

import com.francisco.jdbc.dao.CategoriaDAO;
import com.francisco.jdbc.factory.ConnectionFactory;

import java.sql.SQLException;

public class TestaListagemDeCategorias {

    public static void main(String[] args) throws SQLException {

        try (var connection = new ConnectionFactory().recuperarConexao()) {
            var categoriaDAO = new CategoriaDAO(connection);
            var listaDeCategorias = categoriaDAO.listarComProdutos();
            listaDeCategorias.forEach(categoria -> {
                System.out.println(categoria.getNome());
                for (var produto : categoria.getProdutos()) {
                    System.out.println(categoria.getNome() + " - " + produto.getNome());
                }
            });
        }
    }
}