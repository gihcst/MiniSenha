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
    
    // Array para armazenar a sequência correta de cores
    private Cor[] sequenciaCorreta;
    private int indicePinoSequenciaAtual = 0;

    // Componentes da interface gráfica
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

        this.app.getJogador().setPontuacao(100); // Inicializa a pontuação do jogador

        this.criarInterface(); // Cria a interface gráfica do jogo
    }

    private void criarInterface() {
        frame = new JFrame("Jogo"); // Cria o frame principal com título "Jogo"

        gerarSequenciaAleatoria(); // Gera uma sequência aleatória de cores

        pontosLabel = new JLabel("Pontuação: " + this.app.getJogador().getPontuacao()); // Inicializa a label da pontuação

        Container painelDeConteudo = frame.getContentPane();
        painelDeConteudo.setLayout(new BorderLayout());

        JPanel painelBotoes = criarBotoesPinosColoridos(); // Cria os botões coloridos para interação do usuário
        JPanel painelSequenciaAtual = criarQuadrados(); // Cria os quadrados para mostrar a sequência atual
        JPanel painelDeControle = criarPainelDeControle(); // Cria o painel de controle com botões e informações adicionais

        rodadasPanel = new JPanel();
        rodadasPanel.setLayout(new BoxLayout(rodadasPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(rodadasPanel); // Adiciona rolagem para o painel de rodadas anteriores

        // Adiciona os componentes ao painel principal usando BorderLayout
        painelDeConteudo.add(painelDeControle, BorderLayout.WEST);
        painelDeConteudo.add(painelBotoes, BorderLayout.NORTH);
        painelDeConteudo.add(scrollPane, BorderLayout.CENTER); // Usa o JScrollPane para rolar as rodadas
        painelDeConteudo.add(painelSequenciaAtual, BorderLayout.SOUTH);

        frame.setSize(500, 400); // Define o tamanho do frame
        frame.setLocationRelativeTo(null); // Centraliza o frame na tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão ao fechar o frame
    }

    /**
     * Cria os botões onde o usuário irá interagir para escolher a cor do quadrado.
     */
    private JPanel criarBotoesPinosColoridos() {
        // Inicializa os botões com cores específicas e define os eventos de clique
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
     * Cria os quadrados onde o usuário irá visualizar a sequência atual.
     */
    private JPanel criarQuadrados() {
        JPanel painelDeQuadrados = new JPanel();
        painelDeQuadrados.setLayout(new GridLayout(1, 4, 5, 5)); // Define o layout de grade com espaçamento

        // Inicializa os quadrados com a cor padrão (cinza)
        for (int i = 0; i < sequenciaAtual.length; i++) {
            sequenciaAtual[i] = new Quadrado(Cores.cinza);
            painelDeQuadrados.add(sequenciaAtual[i]);
        }

        return painelDeQuadrados;
    }

    /**
     * Gera uma sequência aleatória de cores que o jogador deve adivinhar.
     */
    private void gerarSequenciaAleatoria() {
        sequenciaCorreta = new Cor[4];
        for (int i = 0; i < 4; i++) {
            int randomIndex = rand.nextInt(Cores.lista.length);
            sequenciaCorreta[i] = Cores.lista[randomIndex];
            System.out.println(sequenciaCorreta[i].getNomeCor()); // Exibe a sequência correta no console (opcional)
        }
    }

    /**
     * Cria o painel de controle com botões e informações adicionais.
     */
    private JPanel criarPainelDeControle() {
        JPanel painelDeControle = new JPanel();
        painelDeControle.setLayout(new BoxLayout(painelDeControle, BoxLayout.PAGE_AXIS));

        JLabel lbConfig = new JLabel("Controles");
        painelDeControle.add(lbConfig); // Adiciona um label para os controles
        JButton btDesfazer = new JButton("Desfazer");
        btDesfazer.addActionListener(e -> desfazerUltimoPasso()); // Define o evento do botão Desfazer
        JButton btSair = new JButton("Sair");
        btSair.addActionListener(e -> app.mostrarMenu()); // Define o evento do botão Sair
        painelDeControle.add(btDesfazer);
        painelDeControle.add(btSair);

        painelDeControle.add(pontosLabel); // Adiciona a label de pontuação atualizada

        return painelDeControle;
    }

    /**
     * Exibe uma mensagem de resultado (vitória, derrota ou erro) para o jogador.
     */
    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(frame, mensagem + "\nPontuação: " + app.getJogador().getPontuacao(), "Resultado!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Avalia o resultado da sequência atual após o jogador completar a tentativa.
     * Atualiza a pontuação e exibe mensagens correspondentes.
     */
    private void fimDaSequencia() {
        if (verificarSequencia()) {
            mostrarMensagem("Vitória!"); // Exibe mensagem de vitória
            Placar.adicionar(this.app.getJogador()); // Adiciona o jogador ao placar de melhores pontuações
            adicionarRodadaAnterior(); // Adiciona a rodada atual ao histórico de rodadas
            this.app.fechar(); // Fecha o aplicativo
        } else {
            app.getJogador().subtrairPontuacao(10); // Penaliza o jogador por erro
            pontosLabel.setText("Pontuação: " + app.getJogador().getPontuacao()); // Atualiza a label de pontuação

            if (app.getJogador().getPontuacao() <= 0) {
                mostrarMensagem("Derrota!"); // Exibe mensagem de derrota se a pontuação for zero ou negativa
                adicionarRodadaAnterior(); // Adiciona a rodada atual ao histórico de rodadas
                this.app.fechar(); // Fecha o aplicativo
            } else {
                mostrarMensagem("Errou!"); // Exibe mensagem de erro padrão
                adicionarRodadaAnterior(); // Adiciona a rodada atual ao histórico de rodadas
                reiniciarJogo(); // Reinicia a sequência atual para uma nova tentativa
            }
        }
    }

        /**
     * Manipula o evento de clique em um dos botões de cor.
     * @param cor A cor selecionada pelo jogador.
     */
    private void eventoBotaoPinoColorido(Cor cor) {
        // Define a cor do quadrado na sequência atual de acordo com o botão clicado
        if (indicePinoSequenciaAtual < 4) {
            sequenciaAtual[indicePinoSequenciaAtual].setCor(cor);
            indicePinoSequenciaAtual++;
        }

        // Verifica se a sequência atual foi completamente preenchida
        if (indicePinoSequenciaAtual >= 4) {
            fimDaSequencia(); // Avalia o resultado da sequência atual
        }
    }

    /**
     * Verifica se a sequência atual de cores escolhidas pelo jogador está correta.
     * @return true se a sequência atual é igual à sequência correta, false caso contrário.
     */
    private boolean verificarSequencia() {
        for (int i = 0; i < 4; i++) {
            if (sequenciaAtual[i].getCor() != sequenciaCorreta[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Desfaz a última ação do jogador, removendo a última cor adicionada na sequência atual.
     */
    private void desfazerUltimoPasso() {
        if (indicePinoSequenciaAtual > 0) {
            indicePinoSequenciaAtual--;
            sequenciaAtual[indicePinoSequenciaAtual].setCor(Cores.cinza); // Define a cor como cinza (não selecionada)
        }
    }

    /**
     * Reinicia a sequência atual, limpando todas as cores selecionadas.
     */
    private void reiniciarJogo() {
        for (int i = 0; i < 4; i++) {
            sequenciaAtual[i].setCor(Cores.cinza); // Define todas as cores como cinza (não selecionadas)
        }
        indicePinoSequenciaAtual = 0; // Reinicia o índice da sequência atual
    }

    /**
     * Adiciona a sequência atual como uma rodada anterior no painel de rodadas.
     */
    private void adicionarRodadaAnterior() {
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());

        // Adiciona os quadrados da sequência atual ao painel de rodadas anteriores
        for (Quadrado quadrado : sequenciaAtual) {
            painel.add(new Quadrado(quadrado.getCor()));
        }

        // Adiciona pistas de dicas sobre a sequência atual ao painel de rodadas anteriores
        painel.add(new PinoPista(gerarDicas()));

        rodadasPanel.add(painel); // Adiciona o painel ao painel de rodadas anteriores

        rodadasPanel.revalidate(); // Atualiza o layout do painel de rodadas para refletir a adição
        rodadasPanel.repaint(); // Redesenha o painel de rodadas para refletir a adição
    }

    /**
     * Gera pistas de dicas sobre a sequência atual comparando com a sequência correta.
     * @return Um ArrayList de cores que indicam pistas sobre a precisão da sequência atual.
     */
    private ArrayList<Cor> gerarDicas() {
        ArrayList<Cor> dicas = new ArrayList<Cor>();
        boolean[] corJaUsada = new boolean[4];

        // Verifica cores na posição correta (cor preta)
        for (int i = 0; i < 4; i++) {
            if (sequenciaAtual[i].getCor() == sequenciaCorreta[i]) {
                dicas.add(Cores.preto);
                corJaUsada[i] = true;
            } else {
                dicas.add(Cores.cinza);
            }
        }

        // Verifica cores presentes mas em posição incorreta (cor branca)
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

        // Embaralha as pistas de dicas para não revelar a ordem correta
        Collections.shuffle(dicas);

        return dicas;
    }

}
