package br.com.michelbarbosa.hearthstonedusthelperlite.mvp.model;

public class StaticCard {

    private String nome;
    private String raridade;
    private String classe;
    private String expansao;

    public StaticCard(String nome, String raridade, String classe, String expansao) {
        this.nome = nome;
        this.raridade = raridade;
        this.classe = classe;
        this.expansao = expansao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaridade() {
        return raridade;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getExpansao() {
        return expansao;
    }

    public void setExpansao(String expansao) {
        this.expansao = expansao;
    }

}
