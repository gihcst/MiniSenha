package minisenha.pinos;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

import minisenha.cor.Cor;

// Classe PinoColorido que estende Pino
public class PinoColorido extends Pino {

    // Interface funcional para lidar com cliques no pino
    public interface PinoClickListener {
        void onClick(Cor cor);
    }

    private PinoClickListener acao; // Listener para o clique no pino
    private Cor cor; // Cor associada ao pino
    private JButton button; // Botão que exibe a cor do pino

    // Construtor que recebe apenas a cor do pino
    public PinoColorido(Cor cor) {
        this.cor = cor;
    }

    // Construtor que recebe a cor do pino e um listener para o clique
    public PinoColorido(Cor cor, PinoClickListener acao) {
        this.acao = acao;

        setOpaque(true); // Define que o painel é opaco
        setBorder(new EmptyBorder(0, 0, 0, 0)); // Define uma borda vazia para o painel

        this.button = new JButton(); // Inicializa o botão
        this.button.setBorder(null); // Remove a borda do botão
        this.button.setPreferredSize(new Dimension(30, 30)); // Define o tamanho preferido do botão
        this.button.setIcon(new ImageIcon(cor.getImageCaminho())); // Define o ícone do botão com base na imagem da cor
        this.button.addActionListener(this::acaoDoBotao); // Adiciona um listener para o clique no botão
    
        this.setCor(cor); // Define a cor do pino

        this.add(this.button); // Adiciona o botão ao painel
    }

    // Método para definir a cor do pino
    public void setCor(Cor cor) {
        this.cor = cor;
        setBackground(this.cor.getCor()); // Define o fundo do painel com a cor do pino
        this.button.setBackground(this.cor.getCor()); // Define o fundo do botão com a cor do pino
        this.button.setIcon(new ImageIcon(this.cor.getImageCaminho())); // Atualiza o ícone do botão com a imagem da cor
    }

    // Método chamado quando ocorre um evento de clique no botão
    public void acaoDoBotao(ActionEvent e) {
        acao.onClick(cor); // Chama o método onClick do listener, passando a cor do pino como argumento
    }

}
