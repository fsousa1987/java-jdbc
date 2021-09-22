package com.francisco.jdbc.controller;

import com.francisco.jdbc.dao.ProdutoDAO;
import com.francisco.jdbc.factory.ConnectionFactory;
import com.francisco.jdbc.modelo.Produto;

import java.util.List;

public class ProdutoController {

    private final ProdutoDAO produtoDAO;

    public ProdutoController() {
        var connection = new ConnectionFactory().recuperarConexao();
        this.produtoDAO = new ProdutoDAO(connection);
    }

    public void deletar(Integer id) {
        this.produtoDAO.deletar(id);
    }

    public void salvar(Produto produto) {
        this.produtoDAO.salvar(produto);
    }

    public List<Produto> listar() {
        return this.produtoDAO.listar();
    }

    public void alterar(String nome, String descricao, Integer id) {
        this.produtoDAO.alterar(nome, descricao, id);
    }
}
