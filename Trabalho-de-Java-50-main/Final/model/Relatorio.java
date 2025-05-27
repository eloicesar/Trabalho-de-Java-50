
package model;

public class Relatorio {

    public void gerar() {
        System.out.println("Relatório geral gerado.");
    }

    public void gerar(String tipoEvento) {
        System.out.println("Relatório para eventos do tipo: " + tipoEvento);
    }

    public void gerar(String tipoEvento, int ano) {
        System.out.println("Relatório do tipo " + tipoEvento + " para o ano " + ano);
    }
}
