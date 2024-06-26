package minisenha;

public class Jogador {

    private String nome; // Nome do jogador
    private int pontuacao; // Pontuação do jogador

    // Construtor padrão que inicializa o jogador com nome vazio e pontuação zero
    public Jogador() {
        this.nome = "";
        this.pontuacao = 0;
    }

    // Construtor que inicializa o jogador com um nome específico e pontuação zero
    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    // Construtor que inicializa o jogador com um valor de pontuação específico e nome vazio
    public Jogador(int pontuacao) {
        this.nome = "";
        this.pontuacao = pontuacao;
    }

    // Construtor que inicializa o jogador com um nome e uma pontuação específica
    public Jogador(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    // Método getter para obter o nome do jogador
    public String getNome() {
        return nome;
    }

    // Método getter para obter a pontuação do jogador
    public int getPontuacao() {
        return pontuacao;
    }

    // Método setter para definir a pontuação do jogador
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    // Método para adicionar pontos à pontuação atual do jogador
    public void adicionarPontuacao(int pontos) {
        this.pontuacao += pontos;
    }

    // Método para subtrair pontos da pontuação atual do jogador
    public void subtrairPontuacao(int pontos) {
        this.pontuacao -= pontos;
    }

    // Método setter para definir o nome do jogador
    public void setNome(String nome) {
        this.nome = nome;
    }

}
