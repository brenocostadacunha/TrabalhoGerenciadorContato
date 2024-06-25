package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabanco.ConnectionFactory;
import model.Contato;
import org.jetbrains.annotations.NotNull;


public class ContatoDao {
	private Connection con;

	public ContatoDao() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public void adiciona(@NotNull final Contato contato) throws SQLException {
		String sql = "insert into contatos(nome, email, endereco) values (?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.execute();
		}
	}

	public List<Contato> getLista() throws SQLException {
		String query = "select * from contatos";
		List<Contato> contatos = new ArrayList<>();

		try (PreparedStatement stmt = con.prepareStatement(query);
			 ResultSet rset = stmt.executeQuery()) {

			while (rset.next()) {
				Contato contato = new Contato();
				contato.setId(rset.getLong("id"));
				contato.setNome(rset.getString("nome"));
				contato.setEmail(rset.getString("email"));
				contato.setEndereco(rset.getString("endereco"));
				contatos.add(contato);
			}
		}

		return contatos;
	}

	public List<Contato> getContatosByNomeInicial(@NotNull final String inicial) throws SQLException {
		String query = "select * from contatos where nome like ?";
		List<Contato> contatos = new ArrayList<>();

		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, inicial + "%");

			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					Contato contato = new Contato();
					contato.setId(rset.getLong("id"));
					contato.setNome(rset.getString("nome"));
					contato.setEmail(rset.getString("email"));
					contato.setEndereco(rset.getString("endereco"));
					contatos.add(contato);
				}
			}
		}

		return contatos;
	}

	public Contato getContatoById(@NotNull final Long id) throws SQLException {
		String query = "select * from contatos where id = ?";
		Contato contato = null;
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setLong(1, id);
			try (ResultSet rset = stmt.executeQuery()) {
				if (rset.next()) {
					contato = new Contato();
					contato.setId(rset.getLong("id"));
					contato.setNome(rset.getString("nome"));
					contato.setEmail(rset.getString("email"));
					contato.setEndereco(rset.getString("endereco"));
				}
			}
		}
		return contato;
	}

	public void altera(@NotNull final Contato contato) throws SQLException {
		String sql = "update contatos set nome = ?, email = ?, endereco = ? where id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setLong(4, contato.getId());
			stmt.execute();
		}
	}

	public void remove(@NotNull final Long id) throws SQLException {
		String sql = "delete from contatos where id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setLong(1, id);
			stmt.execute();
		}
	}

	public void close() throws SQLException {
		con.close();
	}
}
