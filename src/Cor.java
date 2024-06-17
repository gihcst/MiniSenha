import java.awt.Color;

public class Cor {
    private String nomeCor;
    private Color cor;
    private String caminho;

    public Cor(String nomeCor, Color cor, String caminho) {
        this.nomeCor = nomeCor;
        this.cor = cor;
        this.caminho = caminho;
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public Color getCor() {
        return cor;
    }

    public String getImageCaminho() {
        return caminho;
    }

    public String toString(){
        return getNomeCor();
    }
}
