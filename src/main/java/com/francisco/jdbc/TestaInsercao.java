package com.francisco.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {
        var factory = new ConnectionFactory();
        var connection = factory.recuperarConexao();

        var statement = connection.createStatement();
        statement.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('MOUSE', 'MOUSE SEM FIO')",
                Statement.RETURN_GENERATED_KEYS);

        var generatedKeys = statement.getGeneratedKeys();
        while (generatedKeys.next()) {
            var id = generatedKeys.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
    }
}
