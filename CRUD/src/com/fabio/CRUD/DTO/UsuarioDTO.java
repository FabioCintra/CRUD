package com.fabio.CRUD.DTO;

import com.fabio.CRUD.negocio.TypeUser;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabio.CRUD.negocio.validacao.basicas.ValidaEmail;
import com.fabio.CRUD.negocio.validacao.basicas.ValidarNome;
import com.fabio.CRUD.negocio.validacao.basicas.ValidarSenha;

public record UsuarioDTO (String nome,String email,String senha,TypeUser tipo,String dataCriacao,Boolean status){
	
	public UsuarioDTO validacao() throws OperacaoDeUsuarioInvalidoException {
		
		/*
		 * Validacao de email
		 */
		ValidaEmail.validarEmail(email);
		
		/*
		 * validacao de nome
		 */
		ValidarNome.validarNome(nome);
		
		/*
		 * validacaoSenhas
		 */
		ValidarSenha.validarSenha(senha);
		
		return this;
	}
	
}
