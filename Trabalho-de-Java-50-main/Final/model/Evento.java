package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Evento implements Serializable {
    private static final long serialVersionUID = 1L; // Para serialização

    private String titulo;
    private String local;
    private LocalDate data;
    private Organizador organizador;
    private ArrayList<Participante> participantes = new ArrayList<>();

    public Evento(String titulo, String local, LocalDate data, Organizador organizador) {
        this.titulo = titulo;
        this.local = local;
        this.data = data;
        this.organizador = organizador;
    }

    public boolean adicionarParticipante(Participante participante) {
        
        for (Participante p : participantes) {
            if (p.getCpf().equals(participante.getCpf())) {
                System.out.println("Participante já está inscrito neste evento!");
                return false;
            }
        }
        participantes.add(participante);
        return true;
    }

    public void listarParticipantes() {
        if (participantes.isEmpty()) {
            System.out.println("Nenhum participante inscrito neste evento.");
            return;
        }

        System.out.println("Participantes inscritos:");
        for (Participante participante : participantes) {
            System.out.println("- " + participante.getNome() + " (CPF: " + participante.getCpf() + ")");
        }
    }

    public void exibirResumo() {
        System.out.printf("Evento: %s | Local: %s | Data: %s | Organizador: %s | Participantes: %d\n",
                titulo, local, data.toString(), organizador.getNome(), participantes.size());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }
}
