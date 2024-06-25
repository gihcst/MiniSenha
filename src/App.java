import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class App {
    private JFrame menu;
    private JFrame jogo;
    private JPanel[] quadrados;
    private Color[] sequenciaCorreta;
    private Color[] sequenciaAtual = new Color[4];
    private int count = 0;
    private Placar placar;
    private int pontos = 100; // Variável para armazenar os pontos do jogador atual
    private Jogador jogador; // coletar nome e pontos
    private JLabel pontosLabel; // Label para exibir a pontuação
    private JPanel rodadasPanel; // Panel para exibir as rodadas anteriores
    private ArrayList<JPanel[]> rodadasAnteriores; // Lista para armazenar as rodadas anteriores

    Random rand = new Random();

    public App() {
        rodadasAnteriores = new ArrayList<>();
        placar = new Placar();
        jogador = new Jogador();
    }

    public void eventoBotaoJogar(ActionEvent evButtonJogar) {
        jogador.setNome(JOptionPane.showInputDialog(menu, "Digite seu nome:"));
        criaJanelaJogo();
    }

    public void eventoBotaoPlacar(ActionEvent evButtonPlacar) {
        String textoPlacar = placar.carregar();

        JTextArea textArea = new JTextArea(textoPlacar);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Placar", JOptionPane.INFORMATION_MESSAGE);
    }

    public void criaJanelaPrincipal() {
        menu = new JFrame("Menu");
        JButton jogar = new JButton("Jogar");
        JButton placar = new JButton("Placares");

        placar.addActionListener(evButtonPlacar -> eventoBotaoPlacar(evButtonPlacar));
        jogar.addActionListener(evButtonJogar -> eventoBotaoJogar(evButtonJogar));

        Container painelDeConteudo = menu.getContentPane();
        painelDeConteudo.setLayout(new FlowLayout());
        painelDeConteudo.add(jogar);
        painelDeConteudo.add(placar);

        menu.setSize(500, 200);
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }

    public void criaJanelaJogo() {
        jogo = new JFrame("Jogo");

        // adiciona os icones nos botoes
        ImageIcon imgAmarela = new ImageIcon(Paths.get("res", "amarelo.png").toString());
        ImageIcon imgVermelho = new ImageIcon(Paths.get("res", "vermelho.png").toString());
        ImageIcon imgAzul = new ImageIcon(Paths.get("res", "azul.png").toString());
        ImageIcon imgVerde = new ImageIcon(Paths.get("res", "verde.png").toString());
        ImageIcon imgLaranja = new ImageIcon(Paths.get("res", "laranja.png").toString());
        ImageIcon imgRoxo = new ImageIcon(Paths.get("res", "roxo.png").toString());

        // a baixa de cada botão é criado uma nova cor referente a ele
        JButton[] botoes = new JButton[6];
        botoes[0] = new JButton(imgAmarela);
        botoes[0].setBackground(new Color(255, 255, 100));

        botoes[1] = new JButton(imgVermelho);
        botoes[1].setBackground(new Color(255, 134, 117));

        botoes[2] = new JButton(imgAzul);
        botoes[2].setBackground(new Color(25, 25, 112));

        botoes[3] = new JButton(imgVerde);
        botoes[3].setBackground(new Color(166, 255, 143));

        botoes[4] = new JButton(imgLaranja);
        botoes[4].setBackground(new Color(255, 188, 102));

        botoes[5] = new JButton(imgRoxo);
        botoes[5].setBackground(new Color(179, 77, 178));

        Color[] cores = { Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA };
        sequenciaCorreta = new Color[4];
        for (int i = 0; i < 4; i++) {
            int randomIndex = rand.nextInt(cores.length);
            sequenciaCorreta[i] = cores[randomIndex];
        }

        quadrados = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            quadrados[i] = new JPanel();
            quadrados[i].setPreferredSize(new Dimension(30, 30)); // Diminui o tamanho dos quadrados
            quadrados[i].setBackground(Color.GRAY);
        }

        jogador.setPontuacao(pontos);
        pontosLabel = new JLabel("Pontuação: " + pontos); // Inicializa a label da pontuação

        Container painelDeConteudo = jogo.getContentPane();
        painelDeConteudo.setLayout(new BorderLayout());

        JPanel painelDeBotoes = new JPanel();
        painelDeBotoes.setLayout(new FlowLayout());
        for (int i = 0; i < 6; i++) {
            int index = i;
            botoes[i].addActionListener(e -> mudarCorQuadrado(cores[index]));
            painelDeBotoes.add(botoes[i]);
        }

        JPanel painelDeQuadrados = new JPanel();
        painelDeQuadrados.setLayout(new GridLayout(1, 4, 5, 5)); // Adiciona espaçamento entre os quadrados
        for (JPanel quadrado : quadrados) {
            painelDeQuadrados.add(quadrado);
        }

        JPanel painelDeControle = new JPanel();
        painelDeControle.setLayout(new BoxLayout(painelDeControle, BoxLayout.PAGE_AXIS));
        JLabel lbConfig = new JLabel("Controles");
        JButton btVerificar = new JButton("Verificar");
        btVerificar.addActionListener(e -> verifica());
        JButton btSair = new JButton("Sair");
        btSair.addActionListener(e -> System.exit(0));
        painelDeControle.add(lbConfig);
        painelDeControle.add(btVerificar);
        painelDeControle.add(btSair);
        painelDeControle.add(pontosLabel); // Adiciona a label de pontuação

        rodadasPanel = new JPanel();
        rodadasPanel.setLayout(new BoxLayout(rodadasPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(rodadasPanel); // Adiciona rolagem para o painel de rodadas

        painelDeConteudo.add(painelDeControle, BorderLayout.WEST);
        painelDeConteudo.add(painelDeBotoes, BorderLayout.NORTH);
        painelDeConteudo.add(scrollPane, BorderLayout.CENTER); // Usa o JScrollPane aqui
        painelDeConteudo.add(painelDeQuadrados, BorderLayout.SOUTH);

        menu.setVisible(false);
        jogo.setSize(500, 400);
        jogo.setLocationRelativeTo(null);
        jogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jogo.setVisible(true);
    }

    private void mudarCorQuadrado(Color cor) {
        if (count < 4) {
            quadrados[count].setBackground(cor);
            sequenciaAtual[count] = cor;
            count++;
        }

        if (count == 4) {
            if (verificarSequencia()) {
                JOptionPane.showMessageDialog(jogo, "Vitória! Pontuação: " + pontos, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                placar.adicionar(jogador);
                adicionarRodadaAnterior(sequenciaAtual);
                finalizarJogo();
            } else {
                pontos -= 10; // Subtrai 10 pontos em caso de erro
                pontosLabel.setText("Pontuação: " + pontos); // Atualiza a label de pontuação
                if (pontos <= 0) {
                    JOptionPane.showMessageDialog(jogo, "Derrota! Pontuação: " + pontos, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    adicionarRodadaAnterior(sequenciaAtual);
                    finalizarJogo();
                } else {
                    JOptionPane.showMessageDialog(jogo, "Errou! Pontuação: " + pontos, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    adicionarRodadaAnterior(sequenciaAtual);
                    reiniciarJogo();
                }
            }
        }
    }

    // mudar talvez
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

    // mudar para funcionar com botao verificar
    private void adicionarRodadaAnterior(Color[] sequencia) {
        JPanel rodadaContainer = new JPanel();
        rodadaContainer.setLayout(new FlowLayout());

        // Cria os quadrados de cores da rodada
        JPanel[] rodadaPanel = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            rodadaPanel[i] = new JPanel();
            rodadaPanel[i].setPreferredSize(new Dimension(30, 30));
            rodadaPanel[i].setBackground(sequencia[i]);
            rodadaContainer.add(rodadaPanel[i]);
        }

        // Adiciona dicas para a rodada
        JPanel dicasPanel = new JPanel();
        dicasPanel.setLayout(new GridLayout(2, 2));
        JLabel[] dicasLabels = dicas(sequencia);
        for (JLabel dica : dicasLabels) {
            dicasPanel.add(dica);
        }

        rodadaContainer.add(dicasPanel);
        rodadasPanel.add(rodadaContainer);
        rodadasPanel.revalidate();
        rodadasPanel.repaint();
    }

    private JLabel[] dicas(Color[] sequenciaAtual) {
        List<JLabel> dicasLabels = new ArrayList<>();
        boolean[] corJaUsada = new boolean[4];

        // Verifica cores na posição correta
        for (int i = 0; i < 4; i++) {
            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setPreferredSize(new Dimension(15, 15));
            if (sequenciaAtual[i].equals(sequenciaCorreta[i])) {
                label.setBackground(Color.BLACK);
                corJaUsada[i] = true;
            } else {
                label.setBackground(Color.GRAY);
            }
            dicasLabels.add(label);
        }

        // Verifica cores presentes mas em posição incorreta
        for (int i = 0; i < 4; i++) {
            if (!sequenciaAtual[i].equals(sequenciaCorreta[i])) {
                for (int j = 0; j < 4; j++) {
                    if (sequenciaAtual[i].equals(sequenciaCorreta[j]) && !corJaUsada[j]) {
                        JLabel label = new JLabel();
                        label.setOpaque(true);
                        label.setPreferredSize(new Dimension(15, 15));
                        label.setBackground(Color.WHITE);
                        dicasLabels.set(i, label);
                        corJaUsada[j] = true;
                        break;
                    }
                }
            }
        }

        // Embaralha as dicas
        Collections.shuffle(dicasLabels);
        return dicasLabels.toArray(new JLabel[0]);
    }

    private void finalizarJogo() {
        jogo.dispose(); // Fecha a janela do jogo
        menu.dispose(); // Fecha a janela do menu
        System.exit(0); // Encerra a aplicação
    }

    public void verifica() {
        if (count == 4) {
            mudarCorQuadrado(sequenciaAtual[count - 1]);
        }
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