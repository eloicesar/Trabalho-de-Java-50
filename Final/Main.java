// Source code is decompiled from a .class file using FernFlower decompiler.
import controller.ParticipanteController;
import java.util.Scanner;

public class Main {
   public Main() {
   }

   public static void main(String[] var0) {
      ParticipanteController var1 = new ParticipanteController();
      Scanner var2 = new Scanner(System.in);
      int var3 = -1;

      while(var3 != 0) {
         System.out.println("\n=== MENU PARTICIPANTE ===");
         System.out.println("1 - Cadastrar participante");
         System.out.println("2 - Listar participantes");
         System.out.println("3 - Atualizar participante");
         System.out.println("4 - Remover participante");
         System.out.println("0 - Sair");
         System.out.print("Escolha uma opção: ");

         try {
            var3 = Integer.parseInt(var2.nextLine());
         } catch (NumberFormatException var5) {
            System.out.println("Por favor, digite um número válido.");
            continue;
         }

         switch (var3) {
            case 0:
               System.out.println("Encerrando o programa. Até logo!");
               break;
            case 1:
               var1.cadastrar();
               break;
            case 2:
               var1.listar();
               break;
            case 3:
               var1.atualizar();
               break;
            case 4:
               var1.remover();
               break;
            default:
               System.out.println("Opção inválida. Tente novamente.");
         }
      }

      var2.close();
   }
}
