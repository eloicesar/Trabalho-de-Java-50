
import controller.EventoController;
import controller.OrganizadorController;
import controller.ParticipanteController;
import java.util.Scanner;
import model.Evento;
import model.Participante;
import model.Relatorio;
import util.LogUtil;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        OrganizadorController organizadorController = new OrganizadorController(scanner);
        ParticipanteController participanteController = new ParticipanteController(scanner);
        EventoController eventoController = new EventoController(scanner, organizadorController);

        int opcao = -1;

        while (opcao != 0) {
            String[] opcoes = {
                "1 - Gerenciar Participantes",
                "2 - Gerenciar Eventos",
                "3 - Gerenciar Organizadores",
                "4 - Gerar Relatórios",
                "0 - Sair"
            };
            opcao = obterOpcaoMenu("MENU PRINCIPAL", opcoes, scanner);

            switch (opcao) {
                case 1 ->
                    menuParticipantes(participanteController, scanner);
                case 2 ->
                    menuEventos(eventoController, participanteController, scanner);
                case 3 ->
                    menuOrganizadores(organizadorController, scanner);
                case 4 ->
                    menuRelatorios(scanner);
                case 0 -> {
                    System.out.println("Encerrando...");
                    LogUtil.registrar("Sistema encerrado");
                }
                default ->
                    System.out.println("Opção inválida");
            }
        }

        scanner.close();
    }

    private static int obterOpcaoValida(Scanner scanner) {
        int opcao = -1;
        while (opcao == -1) {
            try {
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    System.out.println("Digite um número válido");
                    continue;
                }
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido");
            }
        }
        return opcao;
    }

    private static int obterOpcaoMenu(String titulo, String[] opcoes, Scanner scanner) {
        exibirMenu(titulo, opcoes);
        return obterOpcaoValida(scanner);
    }

    private static void exibirMenu(String titulo, String[] opcoes) {
        System.out.println("\n=== " + titulo + " ===");
        for (String opcao : opcoes) {
            System.out.println(opcao);
        }
        System.out.print("Escolha uma opção: ");
    }

    private static void menuParticipantes(ParticipanteController controller, Scanner scanner) {
        int opcao = -1;

        while (opcao != 0) {
            String[] opcoes = {
                "1 - Cadastrar",
                "2 - Listar",
                "3 - Atualizar",
                "4 - Remover",
                "0 - Voltar"
            };
            opcao = obterOpcaoMenu("Menu Participantes", opcoes, scanner);

            switch (opcao) {
                case 1 ->
                    controller.cadastrar();
                case 2 ->
                    controller.listar();
                case 3 ->
                    controller.atualizar();
                case 4 ->
                    controller.remover();
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void menuEventos(EventoController eventoController, ParticipanteController participanteController,
            Scanner scanner) {
        int opcao = -1;

        while (opcao != 0) {
            String[] opcoes = {
                "1 - Cadastrar evento",
                "2 - Listar eventos",
                "3 - Atualizar evento",
                "4 - Remover evento",
                "5 - Inscrever participante no evento",
                "0 - Voltar"
            };
            opcao = obterOpcaoMenu("Menu Eventos", opcoes, scanner);

            switch (opcao) {
                case 1 ->
                    eventoController.cadastrar();
                case 2 ->
                    eventoController.listar();
                case 3 ->
                    eventoController.atualizar();
                case 4 ->
                    eventoController.remover();
                case 5 ->
                    inscreverParticipanteEvento(eventoController, participanteController, scanner);
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void menuOrganizadores(OrganizadorController controller, Scanner scanner) {
        int opcao = -1;

        while (opcao != 0) {
            String[] opcoes = {
                "1 - Cadastrar",
                "2 - Listar",
                "3 - Atualizar",
                "4 - Remover",
                "0 - Voltar"
            };
            opcao = obterOpcaoMenu("Menu Organizadores", opcoes, scanner);

            switch (opcao) {
                case 1 ->
                    controller.cadastrar();
                case 2 ->
                    controller.listar();
                case 3 ->
                    controller.atualizar();
                case 4 ->
                    controller.remover();
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void menuRelatorios(Scanner scanner) {
        int opcao = -1;
        Relatorio relatorio = new Relatorio();

        while (opcao != 0) {
            String[] opcoes = {
                "1 - Gerar relatório geral",
                "2 - Gerar relatório por tipo",
                "3 - Gerar relatório por tipo e ano",
                "0 - Voltar"
            };
            opcao = obterOpcaoMenu("Menu Relatórios", opcoes, scanner);

            switch (opcao) {
                case 1 -> {
                    relatorio.gerar();
                    LogUtil.registrar("Relatório geral gerado");
                }
                case 2 -> {
                    System.out.print("Digite o tipo de evento: ");
                    String tipo = scanner.nextLine();
                    relatorio.gerar(tipo);
                    LogUtil.registrar("Relatório gerado para tipo: " + tipo);
                }
                case 3 -> {
                    System.out.print("Digite o tipo de evento: ");
                    String tipo = scanner.nextLine();
                    int ano = -1;
                    while (ano == -1) {
                        try {
                            System.out.print("Digite o ano: ");
                            ano = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Digite um ano válido");
                        }
                    }
                    relatorio.gerar(tipo, ano);
                    LogUtil.registrar("Relatório gerado para tipo: " + tipo + " e ano: " + ano);
                }
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void inscreverParticipanteEvento(EventoController eventoController,
            ParticipanteController participanteController, Scanner scanner) {
        if (eventoController.getEventos().isEmpty()) {
            System.out.println("Não há eventos cadastrados");
            return;
        }

        eventoController.listar();

        System.out.print("\nDigite o título do evento para inscrever um participante: ");
        String tituloEvento = scanner.nextLine();

        Evento evento = eventoController.buscarEventoPorTitulo(tituloEvento);

        if (evento == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        if (participanteController.getParticipantes().isEmpty()) {
            System.out.println("Não há participantes cadastrados");
            return;
        }

        participanteController.listar();

        System.out.print("Digite o CPF do participante para inscrever: ");
        String cpfParticipante = scanner.nextLine();

        Participante participante = participanteController.buscarParticipantePorCpf(cpfParticipante);

        if (participante == null) {
            System.out.println("Participante não encontrado!");
            return;
        }

        
        if (evento.adicionarParticipante(participante)) {
            System.out.println("Participante inscrito no evento com sucesso!");
            LogUtil.registrar("Participante " + participante.getNome() + " inscrito no evento " + evento.getTitulo());
        }

        evento.listarParticipantes();
    }
}
