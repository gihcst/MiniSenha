package minisenha.telas;

import javax.swing.JFrame;

// Classe abstrata Tela
public abstract class Tela {

    protected JFrame frame; // JFrame associado à tela

    // Método para mostrar a tela
    public void mostrar() {
        this.frame.setVisible(true); // Torna o JFrame visível
    }

    // Método para esconder a tela
    public void esconder() {
        this.frame.setVisible(false); // Torna o JFrame invisível
    }

    // Método para fechar a tela
    public void fechar() {
        this.frame.dispose(); // Fecha e libera recursos do JFrame
    }

}
