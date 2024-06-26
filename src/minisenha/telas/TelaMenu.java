package minisenha.telas;

import javax.swing.*;

import minisenha.App;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class TelaMenu extends Tela {

    private App app;

    public TelaMenu(App app) {
        this.app = app;
        this.criarInterface();
    }

    private void criarInterface() {
        this.frame = new JFrame("Menu");
        
        Container painelDeConteudo = this.frame.getContentPane();
        painelDeConteudo.setLayout(new FlowLayout());

        JButton botaoJogar = new JButton("Jogar");
        botaoJogar.addActionListener(this::eventoBotaoJogar);
        painelDeConteudo.add(botaoJogar);

        JButton botaoPlacar = new JButton("Placares");
        botaoPlacar.addActionListener(this::eventoBotaoPlacar);
        painelDeConteudo.add(botaoPlacar);

        this.frame.setSize(500, 200);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void eventoBotaoJogar(ActionEvent ev) {
        this.app.getJogador().setNome(JOptionPane.showInputDialog(frame, "Digite seu nome:"));
        this.app.mostrarJogo();
    }

    private void eventoBotaoPlacar(ActionEvent ev) {
        this.app.mostrarPlacar();
    }

}