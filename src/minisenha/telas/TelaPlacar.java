package minisenha.telas;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import minisenha.App;
import minisenha.Placar;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaPlacar extends Tela {

    private App app;

    private JTextArea textArea;

    public TelaPlacar(App app) {
        this.app = app;
        this.frame = new JFrame("Placar"); // Cria o JFrame com título "Placar"
        this.criarInterface(); // Inicializa a interface gráfica
    }

    private void criarInterface() {
        Container painelDeConteudo = this.frame.getContentPane();
        painelDeConteudo.setLayout(new BoxLayout(painelDeConteudo, BoxLayout.PAGE_AXIS)); // Define o layout como BoxLayout na orientação vertical

        textArea = new JTextArea(); // Cria um JTextArea para exibir o placar
        textArea.setEditable(false); // Define que o JTextArea não pode ser editado pelo usuário
        JScrollPane scrollPane = new JScrollPane(textArea); // Cria um JScrollPane para permitir rolagem no JTextArea
        painelDeConteudo.add(scrollPane); // Adiciona o JScrollPane ao conteúdo do JFrame

        JButton botaoFechar = new JButton("Fechar"); // Cria um botão "Fechar"
        botaoFechar.addActionListener(this::eventoBotaoFechar); // Adiciona um ActionListener para o botão "Fechar"
        painelDeConteudo.add(botaoFechar); // Adiciona o botão "Fechar" ao conteúdo do JFrame

        this.frame.setSize(200, 200); // Define o tamanho do JFrame
        this.frame.setLocationRelativeTo(null); // Centraliza o JFrame na tela
        this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Define a operação padrão ao fechar o JFrame (esconde, não encerra o programa)
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                esconder(); // Esconde a tela ao fechar
            }
        });
    }

    /**
     * Método público para mostrar a tela de placar.
     * Carrega os placares e exibe no JTextArea.
     */
    public void mostrar() {
        String placar = Placar.carregar(); // Carrega os placares do jogo (método fictício)
        this.textArea.setText("Placar:\n\n" + placar); // Define o texto do JTextArea com os placares carregados

        this.frame.setVisible(true); // Torna o JFrame visível
    }

    /**
     * Evento associado ao botão "Fechar", que volta ao menu principal.
     * @param e O evento de clique no botão.
     */
    private void eventoBotaoFechar(ActionEvent e) {
        this.app.mostrarMenu(); // Chama o método para mostrar o menu principal da aplicação
    }

}
