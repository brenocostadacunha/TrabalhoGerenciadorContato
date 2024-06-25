package app;

import java.sql.SQLException;

import dao.ContatoDao;
import model.Contato;

public class TestaDaoInsere {
	public static void main(String[] args) {
		Contato contato = new Contato();
		contato.setNome("Maria");
		contato.setEmail("maria@gmail.com");
		contato.setEndereco("Av Brasil, 1000");
		
		try {
			ContatoDao dao = new ContatoDao();
			dao.adiciona(contato);
			System.out.println("Gravação feita no Banco de Dados");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
