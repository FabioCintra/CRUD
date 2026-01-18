package com.fabio.CRUD.negocio.validacao.basicas;

import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabio.CRUD.negocio.usuario.CodigoErroDTO;


/*
 *Essa classe como um todo serve para validar se uma senha possue o minimo necessario para ser uma senha valida
 *
 * Mas tbm se ela nao esta vazio!
 */
public class ValidarSenha {
	
	public static void validarSenha(String senha) throws OperacaoDeUsuarioInvalidoException {
		String regexSenha = "^.{8,20}$";
		
		 
		if(senha.isEmpty()) {
			throw new OperacaoDeUsuarioInvalidoException(CodigoErroDTO.CAMPO_NULO, "Preencha todos os campos antes de confirmar!");
		}
		
		if(!senha.matches(regexSenha)) {
			
			throw new OperacaoDeUsuarioInvalidoException(CodigoErroDTO.TAMANHO_DE_SENHA_INVALIDO, "A senha deve ter entre 8 a 20 caracteres!");
		}
	}
}
