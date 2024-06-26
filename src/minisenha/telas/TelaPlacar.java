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
        this.frame = new JFrame("Placar");
        this.criarInterface();
    }

    private void criarInterface() {
        Container painelDeConteudo = this.frame.getContentPane();
        painelDeConteudo.setLayout(new BoxLayout(painelDeConteudo, BoxLayout.PAGE_AXIS));

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        painelDeConteudo.add(scrollPane);

        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(this::eventoBotaoFechar);
        painelDeConteudo.add(botaoFechar);

        this.frame.setSize(200, 200);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                esconder();
            }
        });
    }

    public void mostrar() {
        String placar = Placar.carregar();
        this.textArea.setText("Placar:\n\n" + placar);

        this.frame.setVisible(true);
    }

    private void eventoBotaoFechar(ActionEvent e) {
        this.app.mostrarMenu();
    }

}