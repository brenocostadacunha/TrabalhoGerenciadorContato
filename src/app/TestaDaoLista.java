package app;

import java.sql.SQLException;
import java.util.List;

import dao.ContatoDao;
import model.Contato;

public class TestaDaoLista {
	public static void main(String[] args) throws SQLException {
		ContatoDao cdao = new ContatoDao();
		List<Contato> contatos = cdao.getLista();
		
		for (Contato contato : contatos) {
			System.out.println("Nome do contato: "+contato.getNome());
			System.out.println("Email do contato: "+contato.getEmail());
			System.out.println("Endereço do contato: "+contato.getEndereco());
			System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		}
	}

}
