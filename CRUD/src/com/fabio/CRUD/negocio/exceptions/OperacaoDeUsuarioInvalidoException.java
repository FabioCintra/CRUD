package com.fabio.CRUD.negocio.exceptions;

import com.fabio.CRUD.negocio.usuario.CodigoErroDTO;

public class OperacaoDeUsuarioInvalidoException extends Exception {
	private CodigoErroDTO erro;

	public OperacaoDeUsuarioInvalidoException(CodigoErroDTO erro, String mensagem) {
		super(mensagem);
		this.erro = erro;
	}

	public CodigoErroDTO getErro() {
		return erro;
	}

}
