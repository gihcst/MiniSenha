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
        jogador = new Jogador();
        telaMenu = new TelaMenu(this);
        telaJogo = new TelaJogo(this);
        telaPlacar = new TelaPlacar(this);
    }

    public Jogador getJogador() {
		return jogador;
	}

    public void mostrarMenu() {
        telaMenu.mostrar();
        telaJogo.esconder();
        telaPlacar.esconder();
    }

    public void mostrarJogo() {
        telaJogo.mostrar();
        telaMenu.esconder();
        telaPlacar.esconder();
    }

    public void mostrarPlacar() {
        telaPlacar.mostrar();
        telaMenu.esconder();
        telaJogo.esconder();
    }

    public void fechar() {
        telaMenu.fechar();
        telaJogo.fechar();
        telaPlacar.fechar();
        System.exit(0);
    }

}