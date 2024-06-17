import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class App {
    private JFrame Menu;
    private JFrame Jogo;

    public void EventoBotaoJogar(ActionEvent evBUttonJogar) {
        Jogo = new JFrame("Jogo");

        JButton Amarelo = new JButton("Amareleo");
        JButton Vermelho = new JButton("Vermelho");
        JButton Azul = new JButton("Azul");
        JButton Verde = new JButton("Verde");
        JButton Laranja = new JButton("Laranja");

        Container painelDeConteudo = Jogo.getContentPane();
        painelDeConteudo.setLayout(new FlowLayout());

        painelDeConteudo.add(Verde);
        painelDeConteudo.add(Laranja);
        painelDeConteudo.add(Azul);
        painelDeConteudo.add(Vermelho);
        painelDeConteudo.add(Verde);
        painelDeConteudo.add(Amarelo);

        Menu.setVisible(false);
        Jogo.setSize(500, 200);
        Jogo.setLocationRelativeTo(null);
        Jogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jogo.setVisible(true);
    }

    public void EventoBotaoPlacar(ActionEvent evButtonPlacar) {

        // aqui devera ter um metodo que colete os 5 primeiros jogadores do placar seus
        // pontos e suas tags
        JOptionPane.showMessageDialog(Jogo, "bumbumba", "placar", 1);
    }

    public void criaJanelaPrincipal() {
        Menu = new JFrame("Menu");
        JButton jogar = new JButton("Jogar");
        JButton placar = new JButton("Placares");

        placar.addActionListener(evButtonPlacar -> EventoBotaoPlacar(evButtonPlacar));
        jogar.addActionListener(evButtonJogar -> EventoBotaoJogar(evButtonJogar));

        Container painelDeConteudo = Menu.getContentPane();
        painelDeConteudo.setLayout(new FlowLayout());
        painelDeConteudo.add(jogar);
        painelDeConteudo.add(placar);

        Menu.setSize(500, 200);
        Menu.setLocationRelativeTo(null);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App app = new App();
                app.criaJanelaPrincipal();
            }
        });
        // System.out.println("Fim");
    }
}
