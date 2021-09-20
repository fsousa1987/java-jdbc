package com.francisco.jdbc.dao;

import com.francisco.jdbc.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        var sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

        try(var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());

            statement.execute();

            try(var resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    produto.setId(resultSet.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        var produtos = new ArrayList<Produto>();

        var sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

        try(var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();

            try(var resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Produto produto =
                            new Produto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));

                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
}
