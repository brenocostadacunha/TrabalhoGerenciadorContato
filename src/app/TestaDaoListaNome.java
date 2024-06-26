package app;

import dao.ContatoDao;
import model.Contato;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestaDaoListaNome {
    public static void main(String[] args) {
        System.out.println("Digite a inicial do nome:");
        try (Scanner scanner = new Scanner(System.in)) {
            String inicial = scanner.nextLine();
            ContatoDao dao = new ContatoDao();
            List<Contato> contatos = dao.getContatosByNomeInicial(inicial);
            dao.close();
            for (Contato contato : contatos) {
                System.out.println("ID: " + contato.getId());
                System.out.println("Nome: " + contato.getNome());
                System.out.println("Email: " + contato.getEmail());
                System.out.println("Endereço: " + contato.getEndereco());
                System.out.println("===================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
