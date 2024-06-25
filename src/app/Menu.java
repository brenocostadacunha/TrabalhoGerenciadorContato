package app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.ContatoDao;
import model.Contato;

public class Menu {
    public static void mostrarMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = 0;
            ContatoDao dao = new ContatoDao();
            while (opcao != 7) {
                System.out.println("Sistema de Gerenciamento de Contatos");
                System.out.println("1. Adicionar Contato");
                System.out.println("2. Listar Contatos");
                System.out.println("3. Listar Contatos por Inicial do Nome");
                System.out.println("4. Buscar Contato por ID");
                System.out.println("5. Alterar Contato");
                System.out.println("6. Remover Contato");
                System.out.println("7. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha pendente
                switch (opcao) {
                    case 1:
                        adicionarContato(dao, scanner);
                        break;
                    case 2:
                        listarContatos(dao);
                        break;
                    case 3:
                        listarContatosPorInicial(dao, scanner);
                        break;
                    case 4:
                        buscarContatoPorId(dao, scanner);
                        break;
                    case 5:
                        alterarContato(dao, scanner);
                        break;
                    case 6:
                        removerContato(dao, scanner);
                        break;
                    case 7:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adicionarContato(ContatoDao dao, Scanner scanner) throws SQLException {
        Contato contato = new Contato();
        try {
            System.out.println("Insira o nome:");
            contato.setNome(scanner.nextLine());

            System.out.println("Insira o email:");
            contato.setEmail(scanner.nextLine());

            System.out.println("Insira o endereço:");
            String endereco = scanner.nextLine();
            if (endereco.length() > 100) {
                throw new IllegalArgumentException("O endereço não pode ter mais de 100 caracteres.");
            }
            contato.setEndereco(endereco);

            dao.adiciona(contato);
            System.out.println("Contato adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar contato: " + e.getMessage());
        }
    }

    private static void listarContatos(ContatoDao dao) throws SQLException {
        List<Contato> contatos = dao.getLista();
        for (Contato contato : contatos) {
            imprimirContato(contato);
        }
    }

    private static void listarContatosPorInicial(ContatoDao dao, Scanner scanner) throws SQLException {
        System.out.println("Digite a inicial do nome:");
        String inicial = scanner.nextLine();
        List<Contato> contatos = dao.getContatosByNomeInicial(inicial);
        for (Contato contato : contatos) {
            imprimirContato(contato);
        }
    }

    private static void buscarContatoPorId(ContatoDao dao, Scanner scanner) throws SQLException {
        System.out.println("Digite o ID do contato:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Contato contato = dao.getContatoById(id);
        if (contato != null) {
            imprimirContato(contato);
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private static void alterarContato(ContatoDao dao, Scanner scanner) throws SQLException {
        System.out.println("Digite o ID do contato a ser alterado:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Contato contato = dao.getContatoById(id);
        if (contato != null) {
            System.out.println("Digite o novo nome (deixe em branco para não alterar):");
            String nome = scanner.nextLine();
            System.out.println("Digite o novo email (deixe em branco para não alterar):");
            String email = scanner.nextLine();
            System.out.println("Digite o novo endereço (deixe em branco para não alterar):");
            String endereco = scanner.nextLine();
            if (!nome.isEmpty()) contato.setNome(nome);
            if (!email.isEmpty()) contato.setEmail(email);
            if (!endereco.isEmpty()) contato.setEndereco(endereco);
            dao.altera(contato);
            System.out.println("Contato alterado com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }
    private static void removerContato(ContatoDao dao, Scanner scanner) throws SQLException {
        System.out.println("Digite o ID do contato a ser removido:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        dao.remove(id);
        System.out.println("Contato removido com sucesso!");
    }
    private static void imprimirContato(Contato contato) {
        System.out.println("ID: " + contato.getId());
        System.out.println("Nome: " + contato.getNome());
        System.out.println("Email: " + contato.getEmail());
        System.out.println("Endereço: " + contato.getEndereco());
        System.out.println("===================================");
    }
}
