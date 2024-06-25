package app;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import javabanco.ConnectionFactory;

public class JdbcInsere {
	public static void main(String[] args) {
		try {
			String sql = "insert into contatos(nome, email, endereco) values (?,?,?)";
			Connection con =  ConnectionFactory.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, "Clayton");
			stmt.setString(2, "clayton@gmail.com");
			stmt.setString(3, "Av. Brasil, 1000");
			
			stmt.execute();
			
			stmt.close();
			con.close();
			
			System.out.println("Gravação feita com sucesso!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
