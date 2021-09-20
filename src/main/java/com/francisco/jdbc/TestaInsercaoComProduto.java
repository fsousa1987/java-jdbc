package com.francisco.jdbc;

import com.francisco.jdbc.modelo.Produto;

import java.sql.*;

public class TestaInsercaoComProduto {

    public static void main(String[] args) throws SQLException {
        var comoda = new Produto("Cômoda", "Cômoda vertical");

        try(var connection = new ConnectionFactory().recuperarConexao()) {
            var sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

            try(var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, comoda.getNome());
                statement.setString(2, comoda.getDescricao());

                statement.execute();

                try(var resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        comoda.setId(resultSet.getInt(1));
                    }
                }
            }
        }
        System.out.println(comoda);
    }
}
