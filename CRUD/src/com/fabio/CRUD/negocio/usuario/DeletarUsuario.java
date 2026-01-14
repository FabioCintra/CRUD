package com.fabio.CRUD.negocio;

import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;

public class DeletarUsuario {
	
	public static void deletarUsuarioPorEmail(String  email, InterfaceDados implemento) throws ErroNaEntradaSaidaExcepiton {
		implemento.deletarUsuarioPeloEmail(email);
	}
}
