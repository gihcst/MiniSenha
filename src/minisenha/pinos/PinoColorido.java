package minisenha.pinos;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

import minisenha.cor.Cor;

public class PinoColorido extends Pino {

    public interface PinoClickListener {
        void onClick(Cor cor);
    }

    private PinoClickListener acao;

    private Cor cor;

    private JButton button;

    public PinoColorido(Cor cor) {
        this.cor = cor;
    }

    public PinoColorido(Cor cor, PinoClickListener acao) {
        this.acao = acao;

        setOpaque(true);
        setBorder(new EmptyBorder(0, 0, 0, 0));

        this.button = new JButton();
        this.button.setBorder(null);
        this.button.setPreferredSize(new Dimension(30, 30));
        this.button.setIcon(new ImageIcon(cor.getImageCaminho()));
        this.button.addActionListener(this::acaoDoBotao);
    
        this.setCor(cor);

        this.add(this.button);
    }

    public void setCor(Cor cor) {
        this.cor = cor;
        setBackground(this.cor.getCor());
        this.button.setBackground(this.cor.getCor());
        this.button.setIcon(new ImageIcon(this.cor.getImageCaminho()));
    }

    public void acaoDoBotao(ActionEvent e) {
        acao.onClick(cor);
    }

}
