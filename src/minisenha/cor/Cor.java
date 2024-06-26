package minisenha.cor;

import java.awt.Color;
import java.nio.file.Paths;

public class Cor {
    private String nomeCor;
    private Color cor;

    public Cor(String nomeCor, Color cor) {
        this.nomeCor = nomeCor;
        this.cor = cor;
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public Color getCor() {
        return cor;
    }

    public String getImageCaminho() {
        return Paths.get("res", this.nomeCor + ".png").toString();
    }

    public String toString(){
        return getNomeCor();
    }
}
