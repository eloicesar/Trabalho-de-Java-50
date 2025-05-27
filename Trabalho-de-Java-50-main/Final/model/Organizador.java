package model;

public class Organizador extends Usuario {
    private String empresa;
    private String cpf;

    public Organizador(String nome, String email, String empresa, String cpf) {
        super(nome, email);
        this.empresa = empresa;
        this.cpf = cpf;
    }

    @Override
    public void exibirDados() {
        System.out.println("[Organizador] Nome: " + nome + " | Email: " + email + " | Empresa: " + empresa + " | CPF: " + cpf);
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
