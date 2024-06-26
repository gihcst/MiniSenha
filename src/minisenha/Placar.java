package minisenha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class Placar {

    private static final int TAMANHO_MAXIMO = 5; // Tamanho máximo do placar
    private static final String CAMINHO_ARQUIVO = Paths.get("res", "placar.csv").toString(); // Caminho do arquivo CSV

    private static ArrayList<Jogador> scores = new ArrayList<Jogador>(); // Lista de jogadores e suas pontuações

    /**
     * Carrega os dados do placar a partir do arquivo CSV.
     * 
     * @return Uma string contendo o conteúdo carregado do arquivo.
     */
    public static String carregar() {
        StringBuilder placarCarregado = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String line;
            while ((line = br.readLine()) != null) {
                placarCarregado.append(line).append("\n");
                String[] campos = line.split(";");
                if (campos.length != 2) {
                    System.out.println("> Número inválido de campos no arquivo CSV...");
                } else {
                    scores.add(new Jogador(campos[0], Integer.parseInt(campos[1])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Tratamento de erro na leitura do arquivo
        }

        return placarCarregado.toString();
    }

    /**
     * Adiciona um jogador ao placar, ordenando e limitando o tamanho máximo.
     * Em seguida, salva o placar atualizado no arquivo CSV.
     * 
     * @param jogador O jogador a ser adicionado ao placar.
     */
    public static void adicionar(Jogador jogador) {
        scores.add(jogador); // Adiciona o novo jogador

        // Ordena os jogadores por pontuação (decrescente)
        scores.sort((a, b) -> b.getPontuacao() - a.getPontuacao());

        // Remove o último jogador se o placar ultrapassar o tamanho máximo
        if (scores.size() > TAMANHO_MAXIMO)
            scores.remove(scores.size() - 1);

        salvar(); // Salva o placar atualizado no arquivo
    }

    /**
     * Salva os dados atuais do placar no arquivo CSV.
     */
    private static void salvar() {
        try {
            PrintWriter out = new PrintWriter(CAMINHO_ARQUIVO);

            // Escreve cada jogador no arquivo no formato "nome;pontuacao"
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
