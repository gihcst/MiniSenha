package minisenha.telas;

import javax.swing.JFrame;

public abstract class Tela {

    protected JFrame frame;

    public void mostrar() {
        this.frame.setVisible(true);
    }

    public void esconder() {
        this.frame.setVisible(false);
    }

    public void fechar() {
        this.frame.dispose();
    }


}