package javabanco;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaFabricaDeConexoes {
	static Connection con;

	public static void main(String[] args) {
		try {
			con = ConnectionFactory.getConnection();
			System.out.println("Conectado ao Banco de Dados Local");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

}
