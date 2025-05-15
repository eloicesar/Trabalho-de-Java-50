package controller;

import model.Participante;
import model.ICadastro;
import java.util.ArrayList;
import java.util.Scanner;

public class ParticipanteController implements ICadastro {
    private ArrayList<Participante> participantes = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void cadastrar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Participante p = new Participante(nome, email, cpf);
        participantes.add(p);

        System.out.println("Participante cadastrado com sucesso!");
    }

    @Override
    public void listar() {
        for (Participante p : participantes) {
            p.exibirDados();
        }
    }

    @Override
    public void atualizar() {
        System.out.print("Digite o CPF do participante para atualizar: ");
        String cpf = scanner.nextLine();

        for (Participante p : participantes) {
            if (p.getCpf().equals(cpf)) {
                System.out.print("Novo nome: ");
                p.setNome(scanner.nextLine());
                System.out.print("Novo email: ");
                p.setEmail(scanner.nextLine());
                System.out.println("Participante atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Participante não encontrado.");
    }

    @Override
    public void remover() {
        System.out.print("Digite o CPF do participante para remover: ");
        String cpf = scanner.nextLine();
        participantes.removeIf(p -> p.getCpf().equals(cpf));
        System.out.println("Participante removido, se existia.");
    }
}
