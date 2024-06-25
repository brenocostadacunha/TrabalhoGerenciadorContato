package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javabanco.ConnectionFactory;

public class JdbcInsere {
	public static void main(String[] args) {
		String sql = "insert into contatos(nome, email, endereco) values (?,?,?)";
		try (Connection con = ConnectionFactory.getConnection();
			 PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, "Clayton");
			stmt.setString(2, "clayton@gmail.com");
			stmt.setString(3, "Av. Brasil, 1000");
			stmt.execute();
			System.out.println("Gravação feita com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
