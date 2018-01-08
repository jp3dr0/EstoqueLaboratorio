package com.jp3dr0.estoquelaboratorio.domain;

/**
 * Created by joaop on 13/12/2017.
 */

public class Usuario {

    int idUsuario;
    String nomeUsuario;
    int Nivel_idNivel;
    String senhaUsuario;
    String emailUsuario;
    String loginUsuario;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nomeUsuario, int nivel_idNivel, String senhaUsuario, String emailUsuario, String loginUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        Nivel_idNivel = nivel_idNivel;
        this.senhaUsuario = senhaUsuario;
        this.emailUsuario = emailUsuario;
        this.loginUsuario = loginUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getNivel_idNivel() {
        return Nivel_idNivel;
    }

    public void setNivel_idNivel(int nivel_idNivel) {
        Nivel_idNivel = nivel_idNivel;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }
}
