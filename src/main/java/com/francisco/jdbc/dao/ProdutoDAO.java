package com.francisco.jdbc.dao;

import com.francisco.jdbc.modelo.Categoria;
import com.francisco.jdbc.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) {
        try {
            var sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

            try (var pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, produto.getNome());
                pstm.setString(2, produto.getDescricao());

                pstm.execute();

                try (var rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvarComCategoria(Produto produto) {
        try {
            var sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)";

            try (var pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, produto.getNome());
                pstm.setString(2, produto.getDescricao());
                pstm.setInt(3, produto.getCategoriaId());

                pstm.execute();

                try (var rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listar() {
        try {
            var produtos = new ArrayList<Produto>();
            var sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

            try (var pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                trasformarResultSetEmProduto(produtos, pstm);
            }
            return produtos;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> buscar(Categoria ct) {
        try {
            var produtos = new ArrayList<Produto>();
            var sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

            try (var pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, ct.getId());
                pstm.execute();

                trasformarResultSetEmProduto(produtos, pstm);
            }
            return produtos;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer id) {
        try {
            try (var stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?")) {
                stm.setInt(1, id);
                stm.execute();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(String nome, String descricao, Integer id) {
        try {
            try (var stm = connection
                    .prepareStatement("UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?")) {
                stm.setString(1, nome);
                stm.setString(2, descricao);
                stm.setInt(3, id);
                stm.execute();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) {
        try {
            try (var rst = pstm.getResultSet()) {
                while (rst.next()) {
                    var produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                    produtos.add(produto);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
