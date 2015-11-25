package com.applications.fronchetti.cbsoft2016.Adapters;

public class Taxi {
    public String nome;
    public String telefone;
    public String imagem;

    public Taxi(String nome, String telefone, String imagem){
        this.nome = nome;
        this.telefone = telefone;
        this.imagem = imagem;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
