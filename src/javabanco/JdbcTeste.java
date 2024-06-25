package javabanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTeste {
	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
		System.out.println("Conectado ao Banco Local");
		con.close();
	}

}
