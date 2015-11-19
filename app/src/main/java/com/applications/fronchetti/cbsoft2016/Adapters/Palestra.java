package com.applications.fronchetti.cbsoft2016.Adapters;

import java.util.Date;

/**
 * Created by Marcia on 08/11/2015.
 */
public class Palestra {
    public String nome;
    public String data;
    public String descricao;
    public String horario;
    public String trabalho;
    public String imagem;
    public String url;


    public String local;

    public Palestra(String nome, String local, String instrutor, String descricao, String horario,
                    String trabalho, String imagem, String url, String data){
        this.nome = nome;
        this.local = local;
        this.instrutor = instrutor;
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
        this.trabalho = trabalho;
        this.imagem = imagem;
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(String trabalho) {
        this.trabalho = trabalho;
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

    public String instrutor;

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

}
