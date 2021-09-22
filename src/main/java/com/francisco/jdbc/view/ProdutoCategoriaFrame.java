package com.francisco.jdbc.view;

import com.francisco.jdbc.controller.CategoriaController;
import com.francisco.jdbc.controller.ProdutoController;
import com.francisco.jdbc.modelo.Categoria;
import com.francisco.jdbc.modelo.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProdutoCategoriaFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTextField textoNome;
    private final JTextField textoDescricao;
    private final JComboBox<Categoria> comboCategoria;
    private final JTable tabela;
    private final DefaultTableModel modelo;
    private final ProdutoController produtoController;
    private final CategoriaController categoriaController;

    public ProdutoCategoriaFrame() {
        super("Produtos");
        var container = getContentPane();
        setLayout(null);

        this.categoriaController = new CategoriaController();
        this.produtoController = new ProdutoController();

        JLabel labelNome = new JLabel("Nome do Produto");
        JLabel labelDescricao = new JLabel("Descrição do Produto");
        JLabel labelCategoria = new JLabel("Categoria do Produto");

        labelNome.setBounds(10, 10, 240, 15);
        labelDescricao.setBounds(10, 50, 240, 15);
        labelCategoria.setBounds(10, 90, 240, 15);

        labelNome.setForeground(Color.BLACK);
        labelDescricao.setForeground(Color.BLACK);
        labelCategoria.setForeground(Color.BLACK);

        container.add(labelNome);
        container.add(labelDescricao);
        container.add(labelCategoria);

        textoNome = new JTextField();
        textoDescricao = new JTextField();
        comboCategoria = new JComboBox<>();

        comboCategoria.addItem(new Categoria(0, "Selecione"));
        var categorias = this.listarCategoria();
        for (Categoria categoria : categorias) {
            comboCategoria.addItem(categoria);
        }

        textoNome.setBounds(10, 25, 265, 20);
        textoDescricao.setBounds(10, 65, 265, 20);
        comboCategoria.setBounds(10, 105, 265, 20);

        container.add(textoNome);
        container.add(textoDescricao);
        container.add(comboCategoria);

        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoLimpar = new JButton("Limpar");

        botaoSalvar.setBounds(10, 145, 80, 20);
        botaoLimpar.setBounds(100, 145, 80, 20);

        container.add(botaoSalvar);
        container.add(botaoLimpar);

        tabela = new JTable();
        modelo = (DefaultTableModel) tabela.getModel();

        modelo.addColumn("Identificador do Produto");
        modelo.addColumn("Nome do Produto");
        modelo.addColumn("Descrição do Produto");

        preencherTabela();

        tabela.setBounds(10, 185, 760, 300);
        container.add(tabela);

        JButton botarApagar = new JButton("Excluir");
        JButton botaoEditar = new JButton("Alterar");

        botarApagar.setBounds(10, 500, 80, 20);
        botaoEditar.setBounds(100, 500, 80, 20);

        container.add(botarApagar);
        container.add(botaoEditar);

        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);

        botaoSalvar.addActionListener(e -> {
            salvar();
            limparTabela();
            preencherTabela();
        });

        botaoLimpar.addActionListener(e -> limpar());

        botarApagar.addActionListener(e -> {
            deletar();
            limparTabela();
            preencherTabela();
        });

        botaoEditar.addActionListener(e -> {
            alterar();
            limparTabela();
            preencherTabela();
        });
    }

    private void limparTabela() {
        modelo.getDataVector().clear();
    }

    private void alterar() {
        Object objetoDaLinha = modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
        if (objetoDaLinha instanceof Integer) {
            Integer id = (Integer) objetoDaLinha;
            String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
            String descricao = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
            this.produtoController.alterar(nome, descricao, id);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void deletar() {
        Object objetoDaLinha = modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
        if (objetoDaLinha instanceof Integer) {
            Integer id = (Integer) objetoDaLinha;
            this.produtoController.deletar(id);
            modelo.removeRow(tabela.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void preencherTabela() {
        List<Produto> produtos = listarProduto();
        for (Produto produto : produtos) {
            modelo.addRow(new Object[] { produto.getId(), produto.getNome(), produto.getDescricao() });
        }
    }

    private List<Categoria> listarCategoria() {
        return this.categoriaController.listar();
    }

    private void salvar() {
        if (!textoNome.getText().equals("") && !textoDescricao.getText().equals("")) {
            Produto produto = new Produto(textoNome.getText(), textoDescricao.getText());
            Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
            assert categoria != null;
            produto.setCategoriaId(categoria.getId());
            this.produtoController.salvar(produto);
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            this.limpar();
        } else {
            JOptionPane.showMessageDialog(this, "Nome e Descri��o devem ser informados.");
        }
    }

    private List<Produto> listarProduto() {
        return this.produtoController.listar();
    }

    private void limpar() {
        this.textoNome.setText("");
        this.textoDescricao.setText("");
        this.comboCategoria.setSelectedIndex(0);
    }
}
