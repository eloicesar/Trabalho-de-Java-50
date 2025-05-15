package util;

import java.io.*;

public class Serializador {

    public static void salvarObjeto(Object obj, String caminho) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(obj);
            System.out.println("Objeto salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar objeto: " + e.getMessage());
        }
    }

    public static Object carregarObjeto(String caminho) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar objeto: " + e.getMessage());
            return null;
        }
    }
}
