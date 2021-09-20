package com.francisco.jdbc;

import java.sql.SQLException;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {
        var factory = new ConnectionFactory();
        var connection = factory.recuperarConexao();

        var statement = connection.prepareStatement("DELETE FROM PRODUTO WHERE id > ?");
        statement.setInt(1, 2);
        statement.execute();

        var linhasModificadas = statement.getUpdateCount();

        System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
    }
}
