package app;

import java.sql.SQLException;
import java.util.Scanner;
import dao.ContatoDao;
import model.Contato;

public class TesteAlteraContato {
    public static void main(String[] args) {
        System.out.println("Digite o ID do contato a ser alterado:");
        try (Scanner scanner = new Scanner(System.in)) {
            Long id = scanner.nextLong();
            scanner.nextLine();
            ContatoDao dao = new ContatoDao();
            Contato contato = dao.getContatoById(id);
            if (contato != null) {
                System.out.println("Digite o novo nome (deixe em branco para não alterar):");
                String nome = scanner.nextLine();
                System.out.println("Digite o novo email (deixe em branco para não alterar):");
                String email = scanner.nextLine();
                System.out.println("Digite o novo endereço (deixe em branco para não alterar):");
                String endereco = scanner.nextLine();
                if (!nome.isEmpty()) {
                    contato.setNome(nome);
                }
                if (!email.isEmpty()) {
                    contato.setEmail(email);
                }
                if (!endereco.isEmpty()) {
                    contato.setEndereco(endereco);
                }
                dao.altera(contato);
                System.out.println("Contato alterado com sucesso!");
            } else {
                System.out.println("Contato não encontrado.");
            }
            dao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
