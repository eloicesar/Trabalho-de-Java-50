import controller.ParticipanteController;
import controller.EventoController;
import model.Evento;
import model.Participante;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParticipanteController participanteController = new ParticipanteController();
        EventoController eventoController = new EventoController();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            String[] opcoes = {
                "1 - Gerenciar Participantes",
                "2 - Gerenciar Eventos",
                "0 - Sair"
            };
            opcao = obterOpcaoMenu("MENU PRINCIPAL", opcoes, scanner);

            switch (opcao) {
                case 1 -> menuParticipantes(participanteController, scanner);
                case 2 -> menuEventos(eventoController, participanteController, scanner);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static int obterOpcaoValida(Scanner scanner) {
        int opcao = -1;
        while (opcao == -1) {
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
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
        String[] opcoes = {
            "1 - Cadastrar",
            "2 - Listar",
            "3 - Atualizar",
            "4 - Remover",
            "0 - Voltar"
        };
        int opcao = obterOpcaoMenu("Menu Participantes", opcoes, scanner);

        switch (opcao) {
            case 1 -> controller.cadastrar();
            case 2 -> controller.listar();
            case 3 -> controller.atualizar();
            case 4 -> controller.remover();
            case 0 -> System.out.println("Voltando ao menu principal...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void menuEventos(EventoController eventoController, ParticipanteController participanteController, Scanner scanner) {
        String[] opcoes = {
            "1 - Cadastrar evento",
            "2 - Listar eventos",
            "3 - Inscrever participante no evento",
            "0 - Voltar"
        };
        int opcao = obterOpcaoMenu("Menu Eventos", opcoes, scanner);

        switch (opcao) {
            case 1 -> eventoController.cadastrar();
            case 2 -> eventoController.listar();
            case 3 -> inscreverParticipanteEvento(eventoController, participanteController, scanner);
            case 0 -> System.out.println("Voltando ao menu principal...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void inscreverParticipanteEvento(EventoController eventoController, ParticipanteController participanteController, Scanner scanner) {
        // Verifica se há eventos cadastrados
        if (eventoController.getEventos().isEmpty()) {
            System.out.println("Não há eventos cadastrados.");
            return;
        }

        // Exibe a lista de eventos disponíveis
        eventoController.listar();

        System.out.print("\nDigite o título do evento para inscrever um participante: ");
        String tituloEvento = scanner.nextLine();

        // Encontra o evento
        Evento evento = eventoController.buscarEventoPorTitulo(tituloEvento);

        if (evento == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        // Verifica se há participantes cadastrados
        if (participanteController.getParticipantes().isEmpty()) {
            System.out.println("Não há participantes cadastrados.");
            return;
        }

        System.out.print("Digite o CPF do participante para inscrever: ");
        String cpfParticipante = scanner.nextLine();

        // Encontra o participante
        Participante participante = participanteController.buscarParticipantePorCpf(cpfParticipante);

        if (participante == null) {
            System.out.println("Participante não encontrado!");
            return;
        }

        // Inscreve o participante no evento
        evento.adicionarParticipante(participante);
        System.out.println("Participante inscrito no evento com sucesso!");

        // Exibe os participantes inscritos
        evento.listarParticipantes();
    }
}
