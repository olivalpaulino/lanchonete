package br.com.dobackaofront.lanchonete.controller;

import br.com.dobackaofront.lanchonete.model.Lanche;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;
import java.util.ArrayList;
import javax.annotation.processing.FilerException;

public class Banco {
    private String url;
    private String usuario;
    private String senha;
    
    public Banco() {
        url = "jdbc:mysql://localhost:3306";
        usuario = "root";
        senha = "root";
    }
    
    public Connection conectar() {
        try {
            url = "jdbc:mysql://localhost:3306/lanchonete";
            usuario = "root";
            senha = "root";
            
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            
            return conexao;
        } catch(SQLException e) {
            
            System.out.println("Não foi possível conectar no banco de dados");
            return null;
        }
    }
    
    public void salvar(String nome, double preco, Connection conexao) {
        String sql = "INSERT INTO lanche(nome, preco) VALUES(?, ?)";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                System.out.println("Lanche foi salvo com sucesso!");
            }
        } catch(SQLException e) {
            System.out.println("O Lanche NÃO foi salvo no banco de dados!");
        }
    }
    
    public void inicializarBanco() {
        url = "jdbc:mysql://localhost:3306";
        usuario = "root";
        senha = "root";
        
        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            Statement stmt = conexao.createStatement();
            
            try {
                InputStream is = new FileInputStream("banco.sql");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                
                String linha;
                StringBuilder sql = new StringBuilder();
                
                linha = br.readLine();
                
                while(linha != null) {
                    System.out.println(linha);
                    sql.append(linha).append("\n");
                    
                    if (linha.trim().endsWith(";")) {
                        stmt.execute(sql.toString());
                        sql.setLength(0);
                    }
                    
                    linha = br.readLine();
                }
                
                stmt.close();
                conexao.close();
            } catch(Exception e) {
                System.out.println("Não foi possível ler o arquivo banco.sql");
            }
            
        } catch(SQLException e) {
            System.out.println("Erro ao conectar no banco de dados no metodo inicializarBanco");
        }
    }
    
    public ArrayList<Lanche> buscarPorTrechoNome(String trechoNome) {
        ArrayList<Lanche> listaDeLanches = new ArrayList<>();
        
        String trechoNormalizado = "%" + normalizarTexto(trechoNome) + "%";
        String sql = "SELECT * FROM lanche WHERE nome LIKE ?";
        try {
            Connection conexao = conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, trechoNormalizado);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                Lanche lanche = new Lanche(nome, preco);
                lanche.setId(id);
                
                listaDeLanches.add(lanche);
            }
            
            rs.close();
            stmt.close();
            conexao.close();
        } catch(SQLException e) {
            System.out.println("Não conseguiu conectar no banco de dados no metodo buscarPorTrechoNome()");
        }
        return listaDeLanches;
    }
    
    private String normalizarTexto(String trecho) {
        return Normalizer.normalize(trecho, Normalizer.Form.NFD).replace("[^\\p{ASCII}]", "");
    }
}
