package com.francisco.jdbc.modelo;

public class Categoria {

    private final Integer id;
    private final String nome;

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
