package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javabanco.ConnectionFactory;
import model.Contato;

public class ContatoDao {
	private Connection con;

	public ContatoDao() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public void adiciona(Contato contato) throws SQLException {
		String sql = "insert into contatos(nome, email, endereco) values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, contato.getNome());
		stmt.setString(2, contato.getEmail());
		stmt.setString(3, contato.getEndereco());

		stmt.execute();
		stmt.close();
	}

	public List<Contato> getLista() throws SQLException {
		String query = "select * from contatos";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();
		List<Contato> contatos = new ArrayList<Contato>();

		while (rset.next()) {
			Contato contato = new Contato();
			contato.setId(rset.getLong("id"));
			contato.setNome(rset.getString("nome"));
			contato.setEmail(rset.getString("email"));
			contato.setEndereco(rset.getString("endereco"));
			contatos.add(contato);
		}

		rset.close();
		stmt.close();
		return contatos;
	}

	public List<Contato> getContatosByNomeInicial(String inicial) throws SQLException {
		String query = "select * from contatos where nome like ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, inicial + "%");
		ResultSet rset = stmt.executeQuery();
		List<Contato> contatos = new ArrayList<Contato>();

		while (rset.next()) {
			Contato contato = new Contato();
			contato.setId(rset.getLong("id"));
			contato.setNome(rset.getString("nome"));
			contato.setEmail(rset.getString("email"));
			contato.setEndereco(rset.getString("endereco"));
			contatos.add(contato);
		}

		rset.close();
		stmt.close();
		return contatos;
	}

	public Contato getContatoById(Long id) throws SQLException {
		String query = "select * from contatos where id = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setLong(1, id);
		ResultSet rset = stmt.executeQuery();
		Contato contato = null;

		if (rset.next()) {
			contato = new Contato();
			contato.setId(rset.getLong("id"));
			contato.setNome(rset.getString("nome"));
			contato.setEmail(rset.getString("email"));
			contato.setEndereco(rset.getString("endereco"));
		}

		rset.close();
		stmt.close();
		return contato;
	}

	public void altera(Contato contato) throws SQLException {
		String sql = "update contatos set nome = ?, email = ?, endereco = ? where id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, contato.getNome());
		stmt.setString(2, contato.getEmail());
		stmt.setString(3, contato.getEndereco());
		stmt.setLong(4, contato.getId());

		stmt.execute();
		stmt.close();
	}

	public void remove(Long id) throws SQLException {
		String sql = "delete from contatos where id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		stmt.execute();
		stmt.close();
	}

	public void close() throws SQLException {
		con.close();
	}
}
