package com.fabio.CRUD.negocio.usuario;

import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;

public class BuscarUsuario {
	
	public Usuario buscarUsuario(String email, InterfaceDados e) throws ErroNaEntradaSaidaExcepiton {
		Usuario user = e.buscaPorEmail(email).orElse(null);
		if(user == null) {
			throw new ErroNaEntradaSaidaExcepiton(CodigoErroDTO.USUARIO_NAO_ENCONTRADO, "Ainda nao ha um usuario cadastrado com esse email!");
		}
		return user;
	}
}
