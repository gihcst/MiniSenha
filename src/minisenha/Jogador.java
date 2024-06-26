package minisenha; 

public class Jogador {

    private String nome;
    private int pontuacao;

    public Jogador() {
        this.nome = "";
        this.pontuacao = 0;
    }

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    public Jogador(int pontuacao) {
        this.nome = "";
        this.pontuacao = pontuacao;
    }

    public Jogador(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void adicionarPontuacao(int pontos) {
        this.pontuacao += pontos;
    }

    public void subtrairPontuacao(int pontos) {
        this.pontuacao -= pontos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}