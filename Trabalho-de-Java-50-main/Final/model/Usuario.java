package model;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L; 

    protected String nome;
    protected String email;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public abstract void exibirDados();

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}