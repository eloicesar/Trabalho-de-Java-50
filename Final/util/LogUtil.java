package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogUtil {
    public static void registrar(String mensagem) {
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            fw.write(LocalDateTime.now() + " - " + mensagem + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao registrar log: " + e.getMessage());
        }
    }
}
