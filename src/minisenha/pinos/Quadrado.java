package minisenha.pinos;

import minisenha.cor.Cor;

import javax.swing.JLabel;
import java.awt.Dimension;

public class Quadrado extends JLabel {

    private Cor cor;

    public Quadrado(Cor cor) {
        this(cor, 30);
    }

    public Quadrado(Cor cor, int tamanho) {
        this.setPreferredSize(new Dimension(tamanho, tamanho));
        this.setOpaque(true);
        this.setCor(cor);
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
        this.setBackground(cor.getCor());
    }

}