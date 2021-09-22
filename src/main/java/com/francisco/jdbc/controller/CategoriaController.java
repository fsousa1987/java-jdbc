package com.francisco.jdbc.controller;

import com.francisco.jdbc.modelo.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

    public List<Categoria> listar() {
        var categorias = new ArrayList<Categoria>();
        categorias.add(new Categoria(1, "Categoria de teste"));
        return categorias;
    }
}
