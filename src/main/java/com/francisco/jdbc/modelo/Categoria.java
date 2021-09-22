package com.francisco.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private final Integer id;
    private final String nome;
    private final List<Produto> produtos = new ArrayList<>();

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
