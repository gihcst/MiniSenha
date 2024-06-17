import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Cores {
    private Map<String, Cor> cores;
    private static Cores instancia = null;

    public static Cores getInstance() {
        if (instancia == null) {
            instancia = new Cores();
        }
        return instancia; 
    }

    private Cores(){
        cores = new HashMap<>();
        cores.put("vermelho", new Cor("vermelho", Color.RED, "imagens/vermelho.png"));
        cores.put("verde", new Cor("verde", Color.GREEN, "imagens/verde.png"));
        cores.put("azul", new Cor("azul", Color.BLUE, "imagens/azul.png"));
        cores.put("laranja", new Cor("laranja", Color.ORANGE, "imagens/laranja.png"));
        cores.put("rosa", new Cor("rosa", Color.PINK, "imagens/rosa.png"));
    }

    public Cor getCor(String key) {
        return cores.get(key);
    }

    public Cor proximaCor(String key) {
        String[] seqCores = cores.keySet().toArray(new String[0]);
        for (int i = 0; i < seqCores.length; i++) {
            if (seqCores[i].equals(key)) {
                return cores.get(seqCores[(i + 1) % seqCores.length]);
            }
        }
        return null;
    }
}
