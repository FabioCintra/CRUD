package com.fabio.CRUD.negocio.validacao.basicas;

import com.fabio.CRUD.negocio.exceptions.CampoNuloException;
import com.fabio.CRUD.negocio.exceptions.TamanhoDeNomeInvalidoException;

public class ValidarNome {
	public static Boolean validarNome(String nome) throws TamanhoDeNomeInvalidoException, CampoNuloException {
		
		String regex = "^.{3,80}$";
		
		if(nome.isEmpty()) {
			throw new CampoNuloException();
		}
		if(!nome.matches(regex)) {
			throw new TamanhoDeNomeInvalidoException();
		}
		
		return true;
	}
}
