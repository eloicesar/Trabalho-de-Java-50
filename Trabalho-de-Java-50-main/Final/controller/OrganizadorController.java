package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.ICadastro;
import model.Organizador;
import util.LogUtil;

public class OrganizadorController implements ICadastro {
    private ArrayList<Organizador> organizadores = new ArrayList<>();
    private Scanner scanner;

    public OrganizadorController(Scanner scanner) {
        this.scanner = scanner;
    }

    public ArrayList<Organizador> getOrganizadores() {
        return organizadores;
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

        System.out.print("Empresa: ");
        String empresa = scanner.nextLine();

        System.out.print("CPF (apenas números): ");
        String cpf = scanner.nextLine();
        while (!cpf.matches("\\d{11}")) {
            System.out.println("CPF inválido. Deve conter 11 dígitos numéricos:");
            cpf = scanner.nextLine();
        }

        
        if (buscarOrganizadorPorCpf(cpf) != null) {
            System.out.println("CPF já cadastrado para outro organizador.");
            return;
        }

        Organizador o = new Organizador(nome, email, empresa, cpf);
        organizadores.add(o);

        LogUtil.registrar("Organizador cadastrado: " + nome + " (CPF: " + cpf + ")");
        System.out.println("Organizador cadastrado com sucesso!");
    }

    @Override
    public void listar() {
        if (organizadores.isEmpty()) {
            System.out.println("Não há organizadores cadastrados.");
            return;
        }

        System.out.println("\n=== Lista de Organizadores ===");
        for (Organizador o : organizadores) {
            o.exibirDados();
        }
    }

    @Override
    public void atualizar() {
        if (organizadores.isEmpty()) {
            System.out.println("Não há organizadores cadastrados para atualizar.");
            return;
        }

        System.out.print("Digite o CPF do organizador para atualizar: ");
        String cpf = scanner.nextLine();

        for (Organizador o : organizadores) {
            if (o.getCpf().equals(cpf)) {
                System.out.print("Novo email (ou Enter para manter): ");
                String novoEmail = scanner.nextLine();
                if (!novoEmail.isEmpty()) {
                    if (novoEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        o.setEmail(novoEmail);
                    } else {
                        System.out.println("Email inválido. Campo não atualizado.");
                    }
                }

                System.out.print("Nova empresa (ou Enter para manter): ");
                String novaEmpresa = scanner.nextLine();
                if (!novaEmpresa.isEmpty()) {
                    o.setEmpresa(novaEmpresa);
                }

                LogUtil.registrar("Organizador atualizado: CPF " + cpf);
                System.out.println("Organizador atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Organizador não encontrado.");
    }

    @Override
    public void remover() {
        if (organizadores.isEmpty()) {
            System.out.println("Não há organizadores cadastrados para remover.");
            return;
        }

        System.out.print("Digite o CPF do organizador para remover: ");
        String cpf = scanner.nextLine();

        boolean removido = organizadores.removeIf(o -> o.getCpf().equals(cpf));

        if (removido) {
            LogUtil.registrar("Organizador removido: CPF " + cpf);
            System.out.println("Organizador removido com sucesso.");
        } else {
            System.out.println("Organizador não encontrado.");
        }
    }

    public Organizador buscarOrganizadorPorCpf(String cpf) {
        for (Organizador org : organizadores) {
            if (org.getCpf().equals(cpf)) {
                return org;
            }
        }
        return null;
    }
}
