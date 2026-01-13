package com.fabio.CRUD.dados.execeptions;

import com.fabio.CRUD.negocio.CodigoErroDTO;

public class ErroNaEntradaSaidaExcepiton extends Exception{
	
	private CodigoErroDTO erro;
	
	public ErroNaEntradaSaidaExcepiton(CodigoErroDTO erro, String mensagem) {
		super(mensagem);
		this.erro = erro;
	}

	public CodigoErroDTO getErro() {
		return erro;
	}
	
	
}
