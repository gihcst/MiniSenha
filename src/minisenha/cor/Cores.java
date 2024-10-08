package minisenha.cor;

import java.awt.Color;

public final class Cores {

    // Criação de instâncias da classe Cor para várias cores
    public static final Cor amarelo = new Cor("amarelo", Color.YELLOW);
    public static final Cor vermelho = new Cor("vermelho", Color.RED);
    public static final Cor verde = new Cor("verde", Color.GREEN);
    public static final Cor azul = new Cor("azul", Color.BLUE);
    public static final Cor laranja = new Cor("laranja", Color.ORANGE);
    public static final Cor roxo = new Cor("roxo", new Color(153, 51, 153)); // Cor roxo com valores RGB específicos
    public static final Cor preto = new Cor("preto", Color.BLACK);
    public static final Cor branco = new Cor("branco", Color.WHITE);
    public static final Cor cinza = new Cor("cinza", Color.GRAY);

    // Array contendo uma lista de algumas cores definidas acima
    public static final Cor[] lista = {amarelo, vermelho, verde, azul, laranja, roxo};
}
