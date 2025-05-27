package controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;
import util.LogUtil;

public class EventoController implements ICadastro {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private OrganizadorController organizadorController;
    private Scanner scanner;

    public EventoController(Scanner scanner, OrganizadorController organizadorController) {
        this.scanner = scanner;
        this.organizadorController = organizadorController;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    @Override
    public void cadastrar() {
        System.out.print("Título do evento: ");
        String titulo = scanner.nextLine();

        System.out.print("Local do evento: ");
        String local = scanner.nextLine();

        LocalDate data = null;
        while (data == null) {
            try {
                System.out.print("Data (AAAA-MM-DD): ");
                data = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use o formato AAAA-MM-DD.");
            }
        }

        
        if (organizadorController.getOrganizadores().isEmpty()) {
            System.out.println("Não há organizadores cadastrados. Cadastre um organizador primeiro.");
            return;
        }

        organizadorController.listar();
        System.out.print("Digite o CPF do organizador: ");
        String cpfOrg = scanner.nextLine();
        Organizador organizador = organizadorController.buscarOrganizadorPorCpf(cpfOrg);

        if (organizador == null) {
            System.out.println("Organizador não encontrado. Deseja cadastrar um novo? (S/N)");
            String opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("S")) {
                organizadorController.cadastrar();
                System.out.print("Digite novamente o CPF do organizador: ");
                cpfOrg = scanner.nextLine();
                organizador = organizadorController.buscarOrganizadorPorCpf(cpfOrg);

                if (organizador == null) {
                    System.out.println("Organizador ainda não encontrado. Evento não cadastrado.");
                    return;
                }
            } else {
                System.out.println("Evento não cadastrado.");
                return;
            }
        }

        Evento evento = new Evento(titulo, local, data, organizador);
        eventos.add(evento);

        LogUtil.registrar("Evento cadastrado: " + titulo);
        System.out.println("Evento cadastrado com sucesso!");
    }

    @Override
    public void listar() {
        if (eventos.isEmpty()) {
            System.out.println("Não há eventos cadastrados.");
            return;
        }

        System.out.println("\n=== Lista de Eventos ===");
        for (Evento e : eventos) {
            e.exibirResumo();
        }
    }

    @Override
    public void atualizar() {
        if (eventos.isEmpty()) {
            System.out.println("Não há eventos cadastrados para atualizar.");
            return;
        }

        System.out.print("Digite o título do evento para atualizar: ");
        String titulo = scanner.nextLine();

        for (Evento e : eventos) {
            if (e.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.print("Novo local (ou Enter para manter): ");
                String novoLocal = scanner.nextLine();
                if (!novoLocal.isEmpty()) {
                    e.setLocal(novoLocal);
                }

                LocalDate novaData = null;
                while (novaData == null) {
                    try {
                        System.out.print("Nova data (AAAA-MM-DD) (ou Enter para manter): ");
                        String dataStr = scanner.nextLine();
                        if (dataStr.isEmpty()) {
                            break;
                        }
                        novaData = LocalDate.parse(dataStr);
                        e.setData(novaData);
                    } catch (DateTimeParseException ex) {
                        System.out.println("Formato de data inválido. Use o formato AAAA-MM-DD.");
                    }
                }

                LogUtil.registrar("Evento atualizado: " + titulo);
                System.out.println("Evento atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Evento não encontrado.");
    }

    @Override
    public void remover() {
        if (eventos.isEmpty()) {
            System.out.println("Não há eventos cadastrados para remover.");
            return;
        }

        System.out.print("Digite o título do evento para remover: ");
        String titulo = scanner.nextLine();

        boolean removido = eventos.removeIf(e -> e.getTitulo().equalsIgnoreCase(titulo));

        if (removido) {
            LogUtil.registrar("Evento removido: " + titulo);
            System.out.println("Evento removido com sucesso.");
        } else {
            System.out.println("Evento não encontrado.");
        }
    }

    public Evento buscarEventoPorTitulo(String titulo) {
        for (Evento evento : eventos) {
            if (evento.getTitulo().equalsIgnoreCase(titulo)) {
                return evento;
            }
        }
        return null;
    }
}
