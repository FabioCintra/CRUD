package com.fabio.CRUD.negocio.validacao.basicas;

import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabio.CRUD.negocio.usuario.CodigoErroDTO;


/*
 *Essa classe como um todo serve para validar se um email possue o minimo necessario para ser um email
 *
 * Mas tbm se ele nao esta vazio!
 * 
 * OBS: A checagem de se o email esta ou nao em uso, eh feito no DTO de usuario!
 */
public class ValidaEmail {
	public static void validarEmail(String email) throws OperacaoDeUsuarioInvalidoException{
		
		String regexEmail = "^[a-zA-z0-9]{5,20}@gmail\\.com";
		
		if(email.isEmpty()) {
			throw new OperacaoDeUsuarioInvalidoException(CodigoErroDTO.CAMPO_NULO, "Preencha todos os campos antes de confirmar!");
		}
		if(!email.matches(regexEmail)) {
			throw new OperacaoDeUsuarioInvalidoException(CodigoErroDTO.FORMATO_EMAIL_ERRADO, "Email escrito errado!");
		}
		
		
	}
	
}
