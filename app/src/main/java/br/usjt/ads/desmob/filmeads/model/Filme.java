package br.usjt.ads.desmob.filmeads.model;

import java.io.Serializable;

public class Filme implements Serializable {

    private int id;
    private String nome, email, diretor, elenco, lancamento, descricao, popularidade, duracao;

    public Filme(int id, String nome, String email, String diretor, String elenco, String lancamento, String descricao, String popularidade, String duracao) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.diretor = diretor;
        this.elenco = elenco;
        this.lancamento = lancamento;
        this.descricao = descricao;
        this.popularidade = popularidade;
        this.duracao = duracao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiretor(){return diretor;}
    public void setDiretor(String diretor) {this.diretor = diretor;}

    public String getElenco(){return elenco;}
    public void setElenco(String elenco) {this.elenco = elenco;}

    public String getLancamento(){return lancamento;}
    public void setLancamento(String lancamento) {this.lancamento = lancamento;}

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPopularidade() {
        return popularidade;
    }
    public void setPopularidade(String popularidade) {
        this.popularidade = popularidade;
    }

    public String getDuracao() {return duracao; }
    public void setDuracao(String duracao) {this.duracao = duracao; }

    public String getFigura(){

        String figura = email.replace('@', '_');
        return figura.replace('.', '_');
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", diretor='" + diretor + '\'' +
                ", elenco='" + elenco + '\'' +
                ", lancamento='" + lancamento + '\'' +
                ", descricao='" + descricao + '\'' +
                ", popularidade=" + popularidade + '\'' +
                ", duracao=" + duracao +
                '}';
    }
}