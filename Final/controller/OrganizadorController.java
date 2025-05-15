package controller;

import model.Organizador;
import model.ICadastro;
import java.util.ArrayList;
import java.util.Scanner;

public class OrganizadorController implements ICadastro {
    private ArrayList<Organizador> organizadores = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void cadastrar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Empresa: ");
        String empresa = scanner.nextLine();

        Organizador o = new Organizador(nome, email, empresa);
        organizadores.add(o);

        System.out.println("Organizador cadastrado com sucesso!");
    }

    @Override
    public void listar() {
        for (Organizador o : organizadores) {
            o.exibirDados();
        }
    }

    @Override
    public void atualizar() {
        System.out.print("Digite o nome do organizador para atualizar: ");
        String nome = scanner.nextLine();

        for (Organizador o : organizadores) {
            if (o.getNome().equalsIgnoreCase(nome)) {
                System.out.print("Novo email: ");
                o.setEmail(scanner.nextLine());
                System.out.print("Nova empresa: ");
                o.setEmpresa(scanner.nextLine());
                System.out.println("Organizador atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Organizador não encontrado.");
    }

    @Override
    public void remover() {
        System.out.print("Digite o nome do organizador para remover: ");
        String nome = scanner.nextLine();
        organizadores.removeIf(o -> o.getNome().equalsIgnoreCase(nome));
        System.out.println("Organizador removido, se existia.");
    }
}
