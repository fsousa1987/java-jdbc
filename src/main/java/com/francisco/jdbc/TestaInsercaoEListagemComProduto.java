package com.francisco.jdbc;

import com.francisco.jdbc.dao.ProdutoDAO;
import com.francisco.jdbc.modelo.Produto;

import java.sql.*;

public class TestaInsercaoEListagemComProduto {

    public static void main(String[] args) throws SQLException {
        var comoda = new Produto("Cômoda", "Cômoda vertical");

        try(var connection = new ConnectionFactory().recuperarConexao()) {
            var produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(comoda);
            var listaDeProdutos = produtoDAO.listar();
            listaDeProdutos.forEach(System.out::println);
        }
    }
}
