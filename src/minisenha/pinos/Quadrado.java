package minisenha.pinos;

import minisenha.cor.Cor;

import javax.swing.JLabel;
import java.awt.Dimension;

// Classe Quadrado que estende JLabel
public class Quadrado extends JLabel {

    private Cor cor; // Cor associada ao quadrado

    // Construtor que recebe apenas a cor
    public Quadrado(Cor cor) {
        this(cor, 30); // Chama o outro construtor com tamanho padrão 30
    }

    // Construtor que recebe a cor e um tamanho específico
    public Quadrado(Cor cor, int tamanho) {
        this.setPreferredSize(new Dimension(tamanho, tamanho)); // Define o tamanho preferido do quadrado
        this.setOpaque(true); // Define que o quadrado é opaco (não transparente)
        this.setCor(cor); // Define a cor do quadrado
    }

    // Método getter para obter a cor do quadrado
    public Cor getCor() {
        return cor;
    }

    // Método setter para definir a cor do quadrado
    public void setCor(Cor cor) {
        this.cor = cor;
        this.setBackground(cor.getCor()); // Define o fundo do quadrado com a cor associada
    }

}
