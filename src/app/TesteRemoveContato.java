package app;

import java.sql.SQLException;
import java.util.Scanner;
import dao.ContatoDao;

public class TesteRemoveContato {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ContatoDao dao = new ContatoDao();
            System.out.println("Digite o ID do contato a ser removido:");
            Long id = scanner.nextLong();
            dao.remove(id);
            System.out.println("Contato removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
