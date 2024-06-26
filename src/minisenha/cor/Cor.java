package minisenha.cor;

import java.awt.Color;
import java.nio.file.Paths;

public class Cor {
    private String nomeCor; // Nome da cor
    private Color cor; // Objeto Color que representa a cor

    // Construtor da classe Cor
    public Cor(String nomeCor, Color cor) {
        this.nomeCor = nomeCor;
        this.cor = cor;
    }

    // Método getter para obter o nome da cor
    public String getNomeCor() {
        return nomeCor;
    }

    // Método getter para obter o objeto Color
    public Color getCor() {
        return cor;
    }

    // Método para obter o caminho da imagem correspondente à cor
    public String getImageCaminho() {
        return Paths.get("res", this.nomeCor + ".png").toString();
    }

    // Método toString que retorna o nome da cor
    public String toString(){
        return getNomeCor();
    }
}
