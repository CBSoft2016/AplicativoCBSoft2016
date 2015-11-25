package com.applications.fronchetti.cbsoft2016.Adapters;

public class Restaurante {
    public String nome;
    public String endereco;
    public String telefone;
    public String imagem;
    public String website;

    public Restaurante(String nome, String endereco, String telefone, String imagem, String website){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.imagem = imagem;
        this.website = website;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
