package com.meetings.models;

public class Sala {
    //private final ImageView fotoSala;
    private String nomeSala;
    private String localizacao;
    private int idSala;

    public Sala(String nomeSala, String localizacao, int idSala) {
        this.nomeSala = nomeSala;
        this.localizacao = localizacao;
        this.idSala = idSala;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }
}
