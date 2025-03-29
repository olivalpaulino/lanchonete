package br.com.dobackaofront.lanchonete;

import br.com.dobackaofront.lanchonete.controller.Banco;
import br.com.dobackaofront.lanchonete.model.Carrinho;
import br.com.dobackaofront.lanchonete.model.Lanche;
import br.com.dobackaofront.lanchonete.view.GUIMenu;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Olival Paulino
 */
public class Lanchonete {

    /*
    public static void main(String[] args) {
        Banco b = new Banco();
        Connection conexao = b.conectar();
        
        Lanche l = new Lanche("Café Gelado", 9.90);
        
        b.salvar(l, conexao);
    }*/
    
    public static void main(String args[]) {
        GUIMenu janelaPrincipal = new GUIMenu();
        Banco b = new Banco();
        b.inicializarBanco();
        
        ArrayList<Lanche> lanches = b.buscarPorTrechoNome("pastel");
        
        for (Lanche lanche: lanches) {
            lanche.apresentarLanche();
        }
        
        b = null;
            
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                janelaPrincipal.setVisible(true);
                janelaPrincipal.getJInternalFrameCadastroLanche().setVisible(false);
                janelaPrincipal.getJInternalFramePesquisar().setVisible(false);
                janelaPrincipal.getJInternalFrameEditarCadastro().setVisible(false);
            }
        });
    }
}
