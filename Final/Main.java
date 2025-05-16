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
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Gerenciar Participantes");
            System.out.println("2 - Gerenciar Eventos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> menuParticipantes(participanteController, scanner);
                case 2 -> menuEventos(eventoController, participanteController, scanner);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static void menuParticipantes(ParticipanteController controller, Scanner scanner) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Menu Participantes ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> controller.cadastrar();
                case 2 -> controller.listar();
                case 3 -> controller.atualizar();
                case 4 -> controller.remover();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuEventos(EventoController eventoController, ParticipanteController participanteController, Scanner scanner) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Menu Eventos ---");
            System.out.println("1 - Cadastrar evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Inscrever participante no evento");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> eventoController.cadastrar();
                case 2 -> eventoController.listar();
                case 3 -> inscreverParticipanteEvento(eventoController, participanteController, scanner);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void inscreverParticipanteEvento(EventoController eventoController, ParticipanteController participanteController, Scanner scanner) {
        // Exibe a lista de eventos disponíveis
        eventoController.listar();

        System.out.print("\nDigite o título do evento para inscrever um participante: ");
        String tituloEvento = scanner.nextLine();

        System.out.print("Digite o CPF do participante para inscrever: ");
        String cpfParticipante = scanner.nextLine();

        // Encontra o evento
        Evento evento = eventoController.buscarEventoPorTitulo(tituloEvento);

        if (evento == null) {
            System.out.println("Evento não encontrado!");
            return;
        }

        // Encontra o participante
        Participante participante = participanteController.buscarParticipantePorCpf(cpfParticipante);

        if (participante == null) {
            System.out.println("Participante não encontrado!");
            return;
        }

        // Inscreve o participante no evento
        evento.adicionarParticipante(participante);
        System.out.println("Participante inscrito no evento com sucesso!");
    }
}
