package com.fabio.CRUD.negocio;

import com.fabio.CRUD.DTO.UsuarioDTO;

public class Usuario {
	private int id;
	private String nome;
	private String email;
	private String senha;
	private TypeUser tipo;
	private String dataCriacao;
	private Boolean status;
	
	public Usuario(UsuarioDTO userDto) {
		
		this.id = GeradorId.idUsuario();
		this.nome = userDto.nome().toUpperCase();
		this.email = userDto.email().toLowerCase();
		this.senha = userDto.senha();
		this.tipo = userDto.tipo();
		this.dataCriacao = userDto.dataCriacao();
		this.status = userDto.status();

	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public TypeUser getTipo() {
		return tipo;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public Boolean getStatus() {
		return status;
	}
	
	
}
