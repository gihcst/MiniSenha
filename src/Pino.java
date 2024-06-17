import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public abstract class Pino extends JButton {
    private Cor cor;

    protected Pino(Cor cor) {
        super(new ImageIcon(cor.getImageCaminho()));
        this.cor = cor;
        this.addActionListener(e -> acaoDoBotao(e));
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
        setIcon(new ImageIcon(cor.getImageCaminho()));
    }

    public abstract void acaoDoBotao(ActionEvent e);
}
