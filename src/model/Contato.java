package model;

import org.jetbrains.annotations.NotNull;

public class Contato {
	private Long id;
	private String nome;
	private String email;
	private String endereco;
	
	public Long getId() {
		return id;
	}

	public void setId(@NotNull final Long id) {
		if (id > 0) {
			this.id = id;
		} else {
			throw new IllegalArgumentException("O ID deve ser maior que zero.");
		}
	}
	public String getNome() {
		return nome;
	}
	
	public void setNome(@NotNull final String nome) {
		if (nome.length() > 100) {
			throw new IllegalArgumentException("O nome não pode ter mais de 100 caracteres.");
		}
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(@NotNull final String email) {
		if (email.length() > 100) {
			throw new IllegalArgumentException("O nome não pode ter mais de 100 caracteres.");
		}
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(@NotNull final String endereco) {
		if (endereco.length() > 100) {
			throw new IllegalArgumentException("O nome não pode ter mais de 100 caracteres.");
		}
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Contato{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", email='" + email + '\'' +
				", endereco='" + endereco + '\'' +
				'}';
	}

	public Contato(@NotNull final Long id, @NotNull final String nome, @NotNull final String email, @NotNull final String endereco) {
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
		this.setEndereco(endereco);
	}
	public Contato(@NotNull final String nome, @NotNull final String email, @NotNull final String endereco) {
		this.setNome(nome);
		this.setEmail(email);
		this.setEndereco(endereco);
	}

	public Contato() {}
}
