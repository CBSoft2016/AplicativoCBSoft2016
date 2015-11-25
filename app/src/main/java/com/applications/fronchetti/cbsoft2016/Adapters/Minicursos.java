package com.applications.fronchetti.cbsoft2016.Adapters;

public class Minicursos {
    String titulo;
    String descricao;
    String local;
    String local_trabalho;
    String data;
    String horario;
    String instrutor;
    String imagem;
    String website;

    public Minicursos(String titulo, String horario, String data, String descricao, String local, String instrutor, String local_trabalho, String imagem, String website){
        this.titulo = titulo;
        this.horario = horario;
        this.data = data;
        this.descricao = descricao;
        this.local = local;
        this.instrutor = instrutor;
        this.local_trabalho = local_trabalho;
        this.imagem = imagem;
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocalTrabalho() {
        return local_trabalho;
    }

    public void setLocalTrabalho(String local_trabalho) {
        this.local_trabalho = local_trabalho;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
