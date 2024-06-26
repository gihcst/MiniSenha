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
        this.frame = new JFrame("Menu"); // Cria o JFrame com título "Menu"
        
        Container painelDeConteudo = this.frame.getContentPane();
        painelDeConteudo.setLayout(new FlowLayout()); // Define o layout do conteúdo como FlowLayout

        JButton botaoJogar = new JButton("Jogar");
        botaoJogar.addActionListener(this::eventoBotaoJogar); // Adiciona um ActionListener para o botão "Jogar"
        painelDeConteudo.add(botaoJogar); // Adiciona o botão "Jogar" ao painel de conteúdo

        JButton botaoPlacar = new JButton("Placares");
        botaoPlacar.addActionListener(this::eventoBotaoPlacar); // Adiciona um ActionListener para o botão "Placares"
        painelDeConteudo.add(botaoPlacar); // Adiciona o botão "Placares" ao painel de conteúdo

        this.frame.setSize(500, 200); // Define o tamanho do JFrame
        this.frame.setLocationRelativeTo(null); // Centraliza o JFrame na tela
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão ao fechar o JFrame
    }

    /**
     * Evento associado ao botão "Jogar", onde o jogador pode digitar seu nome.
     * @param ev O evento de clique no botão.
     */
    private void eventoBotaoJogar(ActionEvent ev) {
        this.app.getJogador().setNome(JOptionPane.showInputDialog(frame, "Digite seu nome:")); // Solicita ao jogador que digite seu nome
        this.app.mostrarJogo(); // Mostra a tela de jogo após o jogador inserir seu nome
    }

    /**
     * Evento associado ao botão "Placares", que mostra os melhores placares.
     * @param ev O evento de clique no botão.
     */
    private void eventoBotaoPlacar(ActionEvent ev) {
        this.app.mostrarPlacar(); // Mostra a tela de placares ao clicar no botão "Placares"
    }

}
