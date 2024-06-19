import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    private JFrame Menu;
    private JFrame Jogo;
    private JPanel[] quadrados;
    private Color[] sequenciaCorreta = { Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN };
    private Color[] sequenciaAtual = new Color[4];
    private int count = 0;

    public void EventoBotaoJogar(ActionEvent evButtonJogar) {
        criaJanelaJogo();
    }

    public void EventoBotaoPlacar(ActionEvent evButtonPlacar) {
        StringBuilder placar = new StringBuilder();
        String filePath = "/Users/gwide/Downloads/MiniSenha-main-2/src/Placar.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                placar.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JTextArea textArea = new JTextArea(placar.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Placar", JOptionPane.INFORMATION_MESSAGE);
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

    public void criaJanelaJogo() {
        Jogo = new JFrame("Jogo");

        JButton[] botoes = new JButton[5];
        botoes[0] = new JButton("Amarelo");
        botoes[1] = new JButton("Vermelho");
        botoes[2] = new JButton("Azul");
        botoes[3] = new JButton("Verde");
        botoes[4] = new JButton("Laranja");

        Color[] cores = { Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE };

        quadrados = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            quadrados[i] = new JPanel();
            quadrados[i].setPreferredSize(new Dimension(50, 50));
            quadrados[i].setBackground(Color.GRAY);
        }

        Container painelDeConteudo = Jogo.getContentPane();
        painelDeConteudo.setLayout(new BorderLayout());

        JPanel painelDeBotoes = new JPanel();
        painelDeBotoes.setLayout(new FlowLayout());
        for (int i = 0; i < 5; i++) {
            int index = i;
            botoes[i].addActionListener(e -> mudarCorQuadrado(cores[index]));
            painelDeBotoes.add(botoes[i]);
        }

        JPanel painelDeQuadrados = new JPanel();
        painelDeQuadrados.setLayout(new GridLayout(1, 4));
        for (JPanel quadrado : quadrados) {
            painelDeQuadrados.add(quadrado);
        }

        painelDeConteudo.add(painelDeBotoes, BorderLayout.NORTH);
        painelDeConteudo.add(painelDeQuadrados, BorderLayout.SOUTH);

        Menu.setVisible(false);
        Jogo.setSize(500, 400);
        Jogo.setLocationRelativeTo(null);
        Jogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jogo.setVisible(true);
    }

    private void mudarCorQuadrado(Color cor) {
        if (count < 4) {
            quadrados[count].setBackground(cor);
            sequenciaAtual[count] = cor;
            count++;
        }

        if (count == 4) {
            if (verificarSequencia()) {
                JOptionPane.showMessageDialog(Jogo, "Vitória", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(Jogo, "Derrota", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            }
            // Reiniciar o jogo após verificar a sequência
            reiniciarJogo();
        }
    }

    private boolean verificarSequencia() {
        for (int i = 0; i < 4; i++) {
            if (!sequenciaAtual[i].equals(sequenciaCorreta[i])) {
                return false;
            }
        }
        return true;
    }

    private void reiniciarJogo() {
        for (int i = 0; i < 4; i++) {
            quadrados[i].setBackground(Color.GRAY);
            sequenciaAtual[i] = null;
        }
        count = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App app = new App();
                app.criaJanelaPrincipal();
            }
        });
    }
}
