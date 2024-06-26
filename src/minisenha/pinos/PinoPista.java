package minisenha.pinos;

import java.awt.GridLayout;
import java.util.List;

import minisenha.cor.Cor;

public class PinoPista extends Pino {

    public PinoPista(List<Cor> cores) {
        this.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < cores.size(); i++) {
            Quadrado quadrado = new Quadrado(cores.get(i), 15);
            this.add(quadrado);
        }
    }
}
