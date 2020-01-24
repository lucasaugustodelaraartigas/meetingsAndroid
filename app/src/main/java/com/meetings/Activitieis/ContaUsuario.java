package com.meetings.Activitieis;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContaUsuario implements  Serializable{
    private int id=0;
    private String senha;
    private String email;


    public ContaUsuario(String senha, String email) {
        this.email = email;
        this.senha = senha;
    }

    private final static List<ContaUsuario> contaUsuarios = new ArrayList<>();

    public List<ContaUsuario> todos() { return new ArrayList<>(contaUsuarios); }

    @NonNull

    public String getSenha() { return senha; }

    public String getEmail() {
        return email;
    }

    public static List<ContaUsuario> getContaUsuarios() {
        return contaUsuarios;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
