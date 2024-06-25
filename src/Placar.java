import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Placar {

    private final int TAMANHO_MAXIMO = 5;
    private final String caminho = Paths.get("res", "placar.csv").toString();

    private ArrayList<Jogador> scores;

    public Placar() {
        scores = new ArrayList<Jogador>();
    }

    public String carregar() {
        // Inicializa o StringBuilder
        StringBuilder inicializa = new StringBuilder();

        // Usa a classe BufferedReader para fazer a leitura do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String line;
            while ((line = br.readLine()) != null) {
                inicializa.append(line).append("\n");
                String[] campos = line.split(";");
                if (campos.length != 2) {
                    System.out.println("> Numero invalido de campos no arquivo CSV...");
                } else {
                    scores.add(new Jogador(campos[0], Integer.parseInt(campos[1])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Indica que houve um erro na leitura
        }

        return inicializa.toString();
    }

    /**
     * o metodo adiciona verifica se ha espaço no arquivo
     * se houver ele salva o nome e pontuação do usuario no arquivo "placar.csv"
     * ele tambem é usado no metodo "salvar"
     * 
     * @param nome
     * @param pontuacao
     * @return void
     */
    public void adicionar(Jogador jogador) {
        // adiciona novo jogador
        scores.add(jogador);
        
        // ordena do maior para o menor
        scores.sort((a, b) -> b.getPontuacao() - a.getPontuacao());

        // remove o ultimo jogador, com menor pontuação
        if (scores.size() > TAMANHO_MAXIMO) scores.remove(scores.size() - 1);

        salvar();
    }

    /**
     * o metodo salvar usa a classe "PrintWriter"(própria do java)
     * para escrever no arquivo "placar.csv"
     * o mesmo apenas escreve, não faz verificação.
     */
    private void salvar() {
        try {
            PrintWriter out = new PrintWriter(caminho);

            for (Jogador jogador : scores) {
                out.printf("%s;%d\n", jogador.getNome(), jogador.getPontuacao());
            }

            out.close();
        } catch (Exception e) {
            System.err.println("Erro ao salvar placar: " + e.getMessage());
            e.printStackTrace();
        }
    }

}