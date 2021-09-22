package com.francisco.jdbc.modelo;

public class Produto {

    private Integer id;
    private final String nome;
    private final String descricao;
    private Integer categoriaId;

    public Produto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    @Override
    public String toString() {
        return String.format("O produto é: %d, %s, %s", this.id, this.nome, this.descricao);
    }
}
