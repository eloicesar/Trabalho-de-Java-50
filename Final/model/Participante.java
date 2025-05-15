
package model;

public class Participante extends Usuario {
    private String cpf;

    public Participante(String nome, String email, String cpf) {
        super(nome, email);
        this.cpf = cpf;
    }

    @Override
    public void exibirDados() {
        System.out.println("[Participante] Nome: " + nome + " | Email: " + email + " | CPF: " + cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
