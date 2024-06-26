package minisenha.telas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import minisenha.App;
import minisenha.Placar;
import minisenha.cor.Cor;
import minisenha.cor.Cores;
import minisenha.pinos.Quadrado;
import minisenha.pinos.PinoColorido;
import minisenha.pinos.PinoPista;

import java.awt.*;

public class TelaJogo extends Tela {

    private App app;

    private Cor[] sequenciaCorreta;
    private int indicePinoSequenciaAtual = 0;

    private JLabel pontosLabel; // Label para exibir a pontuação
    private JPanel rodadasPanel; // Panel para exibir as rodadas anteriores

    /** Botões onde o usuário vai interagir */
    private PinoColorido[] pinosBotoes;

    /** Indicam a rodada atual */
    private Quadrado[] sequenciaAtual;

    Random rand = new Random();

    public TelaJogo(App app) {
        this.app = app;

        pinosBotoes = new PinoColorido[6];
        sequenciaAtual = new Quadrado[4];

        this.app.getJogador().setPontuacao(100);

        this.criarInterface();
    }

    private void criarInterface() {
        frame = new JFrame("Jogo");

        gerarSequenciaAleatoria();

        pontosLabel = new JLabel("Pontuação: " + this.app.getJogador().getPontuacao()); // Inicializa a label da pontuação

        Container painelDeConteudo = frame.getContentPane();
        painelDeConteudo.setLayout(new BorderLayout());

        JPanel painelBotoes = criarBotoesPinosColoridos();
        JPanel painelSequenciaAtual = criarQuadrados();
        JPanel painelDeControle = criarPainelDeControle();

        rodadasPanel = new JPanel();
        rodadasPanel.setLayout(new BoxLayout(rodadasPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(rodadasPanel); // Adiciona rolagem para o painel de rodadas

        painelDeConteudo.add(painelDeControle, BorderLayout.WEST);
        painelDeConteudo.add(painelBotoes, BorderLayout.NORTH);
        painelDeConteudo.add(scrollPane, BorderLayout.CENTER); // Usa o JScrollPane aqui
        painelDeConteudo.add(painelSequenciaAtual, BorderLayout.SOUTH);

        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Botões onde o usuário irá interagir para escolher a cor do quadrado
     */
    private JPanel criarBotoesPinosColoridos() {
        pinosBotoes[0] = new PinoColorido(Cores.amarelo, this::eventoBotaoPinoColorido);
        pinosBotoes[1] = new PinoColorido(Cores.vermelho, this::eventoBotaoPinoColorido);
        pinosBotoes[2] = new PinoColorido(Cores.azul, this::eventoBotaoPinoColorido);
        pinosBotoes[3] = new PinoColorido(Cores.verde, this::eventoBotaoPinoColorido);
        pinosBotoes[4] = new PinoColorido(Cores.laranja, this::eventoBotaoPinoColorido);
        pinosBotoes[5] = new PinoColorido(Cores.roxo, this::eventoBotaoPinoColorido);

        JPanel painelDeBotoes = new JPanel();
        painelDeBotoes.setLayout(new FlowLayout());
        for (int i = 0; i < pinosBotoes.length; i++) {
            painelDeBotoes.add(pinosBotoes[i]);
        }

        return painelDeBotoes;
    }

    /**
     * Quadrados onde o usuário irá visualizar a sequência atual
     */
    private JPanel criarQuadrados() {
        JPanel painelDeQuadrados = new JPanel();
        painelDeQuadrados.setLayout(new GridLayout(1, 4, 5, 5)); // Adiciona espaçamento entre os quadrados

        for (int i = 0; i < sequenciaAtual.length; i++) {
            sequenciaAtual[i] = new Quadrado(Cores.cinza);
            painelDeQuadrados.add(sequenciaAtual[i]);
        }

        return painelDeQuadrados;
    }

    private void gerarSequenciaAleatoria() {
        sequenciaCorreta = new Cor[4];
        for (int i = 0; i < 4; i++) {
            int randomIndex = rand.nextInt(Cores.lista.length);
            sequenciaCorreta[i] = Cores.lista[randomIndex];
            System.out.println(sequenciaCorreta[i].getNomeCor());
        }
    }

    private JPanel criarPainelDeControle() {
        JPanel painelDeControle = new JPanel();
        painelDeControle.setLayout(new BoxLayout(painelDeControle, BoxLayout.PAGE_AXIS));

        JLabel lbConfig = new JLabel("Controles");
        painelDeControle.add(lbConfig);

        JButton btSair = new JButton("Sair");
        btSair.addActionListener(e -> System.exit(0));
        painelDeControle.add(btSair);

        painelDeControle.add(pontosLabel);

        return painelDeControle;
    }

    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(frame, mensagem + "\nPontuação: " + app.getJogador().getPontuacao(), "Resultado!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void fimDaSequencia() {
        if (verificarSequencia()) {
            mostrarMensagem("Vitória!");
            Placar.adicionar(this.app.getJogador());
            adicionarRodadaAnterior();
            this.app.fechar();
        } else {
            app.getJogador().subtrairPontuacao(10);
            pontosLabel.setText("Pontuação: " + app.getJogador().getPontuacao());

            if (app.getJogador().getPontuacao() <= 0) {
                mostrarMensagem("Derrota!");
                adicionarRodadaAnterior();
                this.app.fechar();
            } else {
                mostrarMensagem("Errou!");
                adicionarRodadaAnterior();
                reiniciarJogo();
            }
        }
    }

    private void eventoBotaoPinoColorido(Cor cor) {
        // Se já tiver 4 cores na sequência, verifica se está correta
        if (indicePinoSequenciaAtual < 4) {
            sequenciaAtual[indicePinoSequenciaAtual].setCor(cor);
            
            indicePinoSequenciaAtual++;
        }

        if (indicePinoSequenciaAtual >= 4) {
            fimDaSequencia();
        }
    }

    // mudar talvez
    private boolean verificarSequencia() {
        for (int i = 0; i < 4; i++) {
            if (sequenciaAtual[i].getCor() != sequenciaCorreta[i]) {
                return false;
            }
        }

        return true;
    }

    private void reiniciarJogo() {
        for (int i = 0; i < 4; i++) {
            sequenciaAtual[i].setCor(Cores.cinza);
        }
        indicePinoSequenciaAtual = 0;
    }

    // mudar para funcionar com botao verificar
    private void adicionarRodadaAnterior() {
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());
        
        for (Quadrado quadrado : sequenciaAtual) {
            painel.add(new Quadrado(quadrado.getCor()));
        }
        
        painel.add(new PinoPista(gerarDicas()));
        rodadasPanel.add(painel);

        rodadasPanel.revalidate();
        rodadasPanel.repaint();
    }

    private ArrayList<Cor> gerarDicas() {
        ArrayList<Cor> dicas = new ArrayList<Cor>();
        boolean[] corJaUsada = new boolean[4];

        // Verifica cores na posição correta
        for (int i = 0; i < 4; i++) {
            if (sequenciaAtual[i].getCor() == sequenciaCorreta[i]) {
                dicas.add(Cores.preto);
                corJaUsada[i] = true;
            } else {
                dicas.add(Cores.cinza);
            }
        }

        // Verifica cores presentes mas em posição incorreta
        for (int i = 0; i < 4; i++) {
            if (sequenciaAtual[i].getCor() != sequenciaCorreta[i]) {
                for (int j = 0; j < 4; j++) {
                    if (sequenciaAtual[i].getCor() == sequenciaCorreta[j] && !corJaUsada[j]) {
                        dicas.set(i, Cores.branco);
                        corJaUsada[j] = true;
                        break;
                    }
                }
            }
        }

        // Embaralha as dicas
        Collections.shuffle(dicas);

        return dicas;
    }

}