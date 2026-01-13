package com.fabio.CRUD.negocio.validacao.basicas;

import com.fabio.CRUD.negocio.CodigoErroDTO;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;

public class ValidarNome {
	
	public static void validarNome(String nome) throws OperacaoDeUsuarioInvalidoException {
		String regexNome = "^.{3,80}$";
		
		
		if(nome.isEmpty()) {
			throw new OperacaoDeUsuarioInvalidoException(CodigoErroDTO.CAMPO_NULO, "Preencha todos os campos antes de confirmar!");
		}
		if(!nome.matches(regexNome)) {
			
			throw new OperacaoDeUsuarioInvalidoException( CodigoErroDTO.TAMANHO_DE_NOME_INVALIDO, "O nome deve ter entre 3 a 80 caracteres!");
		}
		
	}
}
