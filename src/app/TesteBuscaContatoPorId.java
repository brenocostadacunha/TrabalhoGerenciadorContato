package app;

import java.sql.SQLException;
import java.util.Scanner;
import dao.ContatoDao;
import model.Contato;

public class TesteBuscaContatoPorId {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do contato:");
        Long id = scanner.nextLong();

        try {
            ContatoDao dao = new ContatoDao();
            Contato contato = dao.getContatoById(id);
            dao.close();

            if (contato != null) {
                System.out.println("ID: " + contato.getId());
                System.out.println("Nome: " + contato.getNome());
                System.out.println("Email: " + contato.getEmail());
                System.out.println("Endereço: " + contato.getEndereco());
            } else {
                System.out.println("Contato não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
