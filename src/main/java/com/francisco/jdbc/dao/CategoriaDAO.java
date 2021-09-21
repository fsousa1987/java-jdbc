package com.francisco.jdbc.dao;

import com.francisco.jdbc.modelo.Categoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private final Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {
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
}
