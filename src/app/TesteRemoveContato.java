package app;

import java.sql.SQLException;
import java.util.Scanner;
import dao.ContatoDao;

public class TesteRemoveContato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do contato a ser removido:");
        Long id = scanner.nextLong();

        try {
            ContatoDao dao = new ContatoDao();
            dao.remove(id);
            dao.close();
            System.out.println("Contato removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
