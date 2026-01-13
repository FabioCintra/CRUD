package com.fabio.CRUD.negocio.validacao.basicas;

import com.fabio.CRUD.negocio.exceptions.CampoNuloException;
import com.fabio.CRUD.negocio.exceptions.TamanhoDeSenhaInvalidoException;

public class ValidarSenha {
	
	public static Boolean validarSenha(String senha) throws TamanhoDeSenhaInvalidoException, CampoNuloException {
		String regex = "^.{8,20}$";
		
		if(senha.isEmpty()) {
			throw new CampoNuloException();
		}
		if(!senha.matches(regex)) {
			throw new TamanhoDeSenhaInvalidoException();
		}
		
		return true;
	}
}
