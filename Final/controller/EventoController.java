package controller;

import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class EventoController implements ICadastro {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void cadastrar() {
        System.out.print("Título do evento: ");
        String titulo = scanner.nextLine();

        System.out.print("Local do evento: ");
        String local = scanner.nextLine();

        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());

        System.out.print("Nome do organizador: ");
        String nome = scanner.nextLine();

        System.out.print("Email do organizador: ");
        String email = scanner.nextLine();

        System.out.print("Empresa do organizador: ");
        String empresa = scanner.nextLine();

        Organizador org = new Organizador(nome, email, empresa);
        Evento ev = new Evento(titulo, local, data, org);
        eventos.add(ev);

        System.out.println("Evento cadastrado com sucesso!");
    }

    @Override
    public void listar() {
        for (Evento e : eventos) {
            e.exibirResumo();
        }
    }

    @Override
    public void atualizar() {
        System.out.print("Digite o título do evento para atualizar: ");
        String titulo = scanner.nextLine();

        for (Evento e : eventos) {
            if (e.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.print("Novo local: ");
                e.setLocal(scanner.nextLine());
                System.out.print("Nova data (AAAA-MM-DD): ");
                e.setData(LocalDate.parse(scanner.nextLine()));
                System.out.println("Evento atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Evento não encontrado.");
    }

    @Override
    public void remover() {
        System.out.print("Digite o título do evento para remover: ");
        String titulo = scanner.nextLine();
        eventos.removeIf(e -> e.getTitulo().equalsIgnoreCase(titulo));
        System.out.println("Evento removido, se existia.");
    }
}
