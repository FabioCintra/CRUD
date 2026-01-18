package com.fabio.CRUD.negocio.usuario;

import java.io.Serializable;

import com.fabio.CRUD.DTO.UsuarioDTO;

public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	//Para quando houver necessidade de buscas e o user nao for encontrado, para poder retornar um usuario vazio
	public  Usuario() {};
	
	public Usuario(Usuario user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.senha = user.getSenha();
		this.tipo = user.getTipo();
		this.dataCriacao = user.getDataCriacao();
		this.status = user.getStatus();
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
	
	
	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setTipo(TypeUser tipo) {
		this.tipo = tipo;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public void infoUser() {
		if(this.tipo == TypeUser.ADMIN) {
			System.out.println("\t-ADMINISTRADOR-\n");
		}
		else {
			System.out.println("\t-USUARIO-\n");
		}
		System.out.println("Id: " + this.id + "\nNome: "+ this.nome + "\nEmail: " + this.email +"\nSenha: " + this.senha +"\nLoguin: " + this.dataCriacao + "\nStatus: "+ this.status+"\n");
	}
	
	public Boolean equals(Usuario user) {
		
		if(user.getStatus() == this.status && user.getDataCriacao().equals(this.dataCriacao) && user.getEmail().equals(this.email) && user.getId() == this.id && user.getNome().equals(this.nome)  &&
			user.getSenha().equals(this.senha) && user.getTipo() == this.tipo) {
			return true;
		}
		
		return false;
	}
}
