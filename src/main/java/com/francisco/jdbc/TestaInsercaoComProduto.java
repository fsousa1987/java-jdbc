package com.francisco.jdbc;

import com.francisco.jdbc.modelo.Produto;

import java.sql.*;

public class TestaInsercaoComProduto {

    public static void main(String[] args) throws SQLException {
        Produto comoda = new Produto("Cômoda", "Cômoda vertical");

        try(Connection connection = new ConnectionFactory().recuperarConexao()) {
            String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

            try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, comoda.getNome());
                statement.setString(2, comoda.getDescricao());

                statement.execute();

                try(ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        comoda.setId(resultSet.getInt(1));
                    }
                }
            }
        }
        System.out.println(comoda);
    }
}
