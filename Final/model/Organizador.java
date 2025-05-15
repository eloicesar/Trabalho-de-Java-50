
package model;

public class Organizador extends Usuario {
    private String empresa;

    public Organizador(String nome, String email, String empresa) {
        super(nome, email);
        this.empresa = empresa;
    }

    @Override
    public void exibirDados() {
        System.out.println("[Organizador] Nome: " + nome + " | Email: " + email + " | Empresa: " + empresa);
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
