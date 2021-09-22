package com.francisco.jdbc.dao;

import com.francisco.jdbc.modelo.Categoria;
import com.francisco.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() {
        try {
            var categorias = new ArrayList<Categoria>();

            System.out.println("Executando a query de listar categoria");

            var sql = "SELECT ID, NOME FROM CATEGORIA";

            try (var preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.execute();

                try (var resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        var categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));
                        categorias.add(categoria);
                    }
                }
            }
            return categorias;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Categoria> listarComProdutos() {
        try {
            Categoria ultima = null;

            var categorias = new ArrayList<Categoria>();

            System.out.println("Executando a query de listar categoria");

            var sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN PRODUTO P ON C.id = P.categoria_id";

            try (var preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.execute();

                try (var resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        if (ultima == null || !ultima.getNome().equals(resultSet.getString(2))) {
                            var categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));
                            ultima = categoria;
                            categorias.add(categoria);
                        }
                        var produto = new Produto(resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
                        ultima.adicionar(produto);
                    }
                }
            }
            return categorias;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
