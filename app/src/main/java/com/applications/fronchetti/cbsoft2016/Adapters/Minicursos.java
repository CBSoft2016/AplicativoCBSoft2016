package com.applications.fronchetti.cbsoft2016.Adapters;

public class Minicursos {
    String nome;
    String local;
    String instrutor;

    public Minicursos(String nome, String local, String instrutor){
        this.nome = nome;
        this.local = local;
        this.instrutor = instrutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
