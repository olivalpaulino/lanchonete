/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dobackaofront.lanchonete.model;

import br.com.dobackaofront.lanchonete.controller.Banco;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Família Santos
 */
public class Lanche {
    private int id;
    private String nome;
    private double preco;
    
    public Lanche() {
        this.id = 0;
        this.nome = "";
        this.preco = 0;
    }
    
    public Lanche(String nome, double preco) {
        this.id = 0;
        this.nome = nome;
        this.preco = preco;
        
        //salvar(nome, preco);
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public void apresentarLanche() {
        System.out.println("Nome: "+nome+", R$ "+preco+", ID: "+id);
    }
    
    
    
    public void salvar(String nome, double preco) {
        Banco b = new Banco();
        Connection conexao = b.conectar();
        b.salvar(nome, preco, conexao);
    }
    
    public ArrayList<Lanche> pesquisar(String nome) {
        Banco b = new Banco();
        Connection conexao = b.conectar();
        ArrayList<Lanche> lanches = b.buscarPorTrechoNome(nome);
        
        return lanches;
    }
    
    public void deletar(int id) {
        Banco b = new Banco();
        Connection conexao = b.conectar();
        b.deletar(id);
        
    }
    
    public void editar(String nome, double preco, int id) {
        Banco b = new Banco();
        Connection conexao = b.conectar();
        b.editar(nome, preco, id);
    }
    
    public Lanche bucarPorId(int id){
        Banco b = new Banco();
        Connection conexao = b.conectar();
        Lanche lanche = b.buscarPorId(id);
        return lanche;
    }
    
    public void adicionarCarrinho(int id, int quantidade) {
        Lanche lanche = bucarPorId(id);
        Banco b = new Banco();
        Connection conexao = b.conectar();
        b.adicionarCarrinho(lanche, quantidade, conexao);
    }
    
    public ArrayList<Lanche> recuperarCarrinho() {
        Banco b = new Banco();
        Connection conexao = b.conectar();
        ArrayList<Lanche> lanches = b.buscarCarrinho();
        return lanches;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
