package com.francisco.jdbc.dao;

import com.francisco.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
