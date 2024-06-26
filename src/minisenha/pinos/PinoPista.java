package minisenha.pinos;

import java.awt.GridLayout;
import java.util.List;

import minisenha.cor.Cor;

// Classe PinoPista que estende Pino
public class PinoPista extends Pino {

    // Construtor que recebe uma lista de cores
    public PinoPista(List<Cor> cores) {
        this.setLayout(new GridLayout(2, 2)); // Define o layout do painel como GridLayout de 2 linhas por 2 colunas

        // Itera sobre a lista de cores
        for (int i = 0; i < cores.size(); i++) {
            Quadrado quadrado = new Quadrado(cores.get(i), 15); // Cria um quadrado com a cor atual e tamanho 15
            this.add(quadrado); // Adiciona o quadrado ao painel PinoPista
        }
    }
}
