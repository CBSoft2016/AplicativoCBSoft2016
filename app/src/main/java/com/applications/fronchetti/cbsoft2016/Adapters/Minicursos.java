package com.applications.fronchetti.cbsoft2016.Adapters;

public class Minicursos {
    String titulo;
    String horario;
    String data;
    String descricao;
    String local;
    String instrutor;
    String localdetrabalho;
    String imagem;
    String url;

    public Minicursos(String titulo, String horario, String data, String descricao, String local, String instrutor,
                      String localdetrabalho, String imagem, String url){
        this.titulo = titulo;
        this.horario = horario;
        this.data = data;
        this.descricao = descricao;
        this.local = local;
        this.instrutor = instrutor;
        this.localdetrabalho = localdetrabalho;
        this.imagem = imagem;
        this.url = url;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocaldetrabalho() {
        return localdetrabalho;
    }

    public void setLocaldetrabalho(String localdetrabalho) {
        this.localdetrabalho = localdetrabalho;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }
}
