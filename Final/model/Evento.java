
package model;

import java.time.LocalDate;

public class Evento {
    private String titulo;
    private String local;
    private LocalDate data;
    private Organizador organizador;

    public Evento(String titulo, String local, LocalDate data, Organizador organizador) {
        this.titulo = titulo;
        this.local = local;
        this.data = data;
        this.organizador = organizador;
    }

    public void exibirResumo() {
        System.out.printf("Evento: %s | Local: %s | Data: %s | Organizador: %s\n",
                titulo, local, data.toString(), organizador.getNome());
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Organizador getOrganizador() { return organizador; }
    public void setOrganizador(Organizador organizador) { this.organizador = organizador; }
}
