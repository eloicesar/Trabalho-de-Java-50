package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.ICadastro;
import model.Participante;
import util.LogUtil;

public class ParticipanteController implements ICadastro {
    private ArrayList<Participante> participantes = new ArrayList<>();
    private Scanner scanner;

    public ParticipanteController(Scanner scanner) {
        this.scanner = scanner;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    @Override
    public void cadastrar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        while (nome.trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio. Digite novamente:");
            nome = scanner.nextLine();
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("Email inválido. Tente novamente:");
            email = scanner.nextLine();
        }

        System.out.print("CPF (apenas números): ");
        String cpf = scanner.nextLine();

        while (!cpf.matches("\\d{11}")) {
            System.out.println("CPF inválido. Deve conter 11 dígitos numéricos:");
            cpf = scanner.nextLine();
        }

       
        if (buscarParticipantePorCpf(cpf) != null) {
            System.out.println("CPF já cadastrado para outro participante.");
            return;
        }

        Participante p = new Participante(nome, email, cpf);
        participantes.add(p);

        LogUtil.registrar("Participante cadastrado: " + nome + " (CPF: " + cpf + ")");
        System.out.println("Participante cadastrado com sucesso!");
    }

    public Participante buscarParticipantePorCpf(String cpf) {
        for (Participante participante : participantes) {
            if (participante.getCpf().equals(cpf)) {
                return participante;
            }
        }
        return null;
    }

    @Override
    public void listar() {
        if (participantes.isEmpty()) {
            System.out.println("Não há participantes cadastrados.");
            return;
        }

        System.out.println("\n=== Lista de Participantes ===");
        for (Participante p : participantes) {
            p.exibirDados();
        }
    }

    @Override
    public void atualizar() {
        if (participantes.isEmpty()) {
            System.out.println("Não há participantes cadastrados para atualizar.");
            return;
        }

        System.out.print("Digite o CPF do participante para atualizar: ");
        String cpf = scanner.nextLine();

        for (Participante p : participantes) {
            if (p.getCpf().equals(cpf)) {
                System.out.print("Novo nome (ou Enter para manter): ");
                String novoNome = scanner.nextLine();
                if (!novoNome.isEmpty()) {
                    p.setNome(novoNome);
                }

                System.out.print("Novo email (ou Enter para manter): ");
                String novoEmail = scanner.nextLine();
                if (!novoEmail.isEmpty()) {
                    if (novoEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        p.setEmail(novoEmail);
                    } else {
                        System.out.println("Email inválido. Campo não atualizado.");
                    }
                }

                LogUtil.registrar("Participante atualizado: CPF " + cpf);
                System.out.println("Participante atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Participante não encontrado.");
    }

    @Override
    public void remover() {
        if (participantes.isEmpty()) {
            System.out.println("Não há participantes cadastrados para remover.");
            return;
        }

        System.out.print("Digite o CPF do participante para remover: ");
        String cpf = scanner.nextLine();

        boolean removido = participantes.removeIf(p -> p.getCpf().equals(cpf));

        if (removido) {
            LogUtil.registrar("Participante removido: CPF " + cpf);
            System.out.println("Participante removido com sucesso.");
        } else {
            System.out.println("Participante não encontrado.");
        }
    }
}
