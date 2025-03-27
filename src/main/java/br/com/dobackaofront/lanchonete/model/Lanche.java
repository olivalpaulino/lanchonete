/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dobackaofront.lanchonete.model;

import br.com.dobackaofront.lanchonete.controller.Banco;
import java.sql.Connection;

/**
 *
 * @author Família Santos
 */
public class Lanche {
    private int id;
    private String nome;
    private double preco;
    
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
