package minisenha;

import minisenha.telas.Tela;
import minisenha.telas.TelaJogo;
import minisenha.telas.TelaMenu;
import minisenha.telas.TelaPlacar;

public class App {
    
    private Jogador jogador;

    private Tela telaMenu;
    private Tela telaJogo;
    private Tela telaPlacar;

    public App() {
        jogador = new Jogador(); // Inicializa o jogador

        // Inicializa as telas do jogo passando esta instância de App como parâmetro
        telaMenu = new TelaMenu(this);
        telaJogo = new TelaJogo(this);
        telaPlacar = new TelaPlacar(this);
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void mostrarMenu() {
        telaMenu.mostrar(); // Mostra a tela do menu
        telaJogo.esconder(); // Esconde a tela do jogo
        telaPlacar.esconder(); // Esconde a tela do placar
    }

    public void mostrarJogo() {
        telaJogo.mostrar(); // Mostra a tela do jogo
        telaMenu.esconder(); // Esconde a tela do menu
        telaPlacar.esconder(); // Esconde a tela do placar
    }

    public void mostrarPlacar() {
        telaPlacar.mostrar(); // Mostra a tela do placar
        telaMenu.esconder(); // Esconde a tela do menu
        telaJogo.esconder(); // Esconde a tela do jogo
    }

    public void fechar() {
        telaMenu.fechar(); // Fecha a tela do menu
        telaJogo.fechar(); // Fecha a tela do jogo
        telaPlacar.fechar(); // Fecha a tela do placar
        System.exit(0); // Encerra a aplicação
    }

}
