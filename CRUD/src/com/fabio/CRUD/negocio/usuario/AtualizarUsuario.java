package com.fabio.CRUD.negocio.usuario;

import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;

public class AtualizarUsuario {
	
	public static void atualizandoUsuario(String emailChave, Usuario atualizado, InterfaceDados e) throws ErroNaEntradaSaidaExcepiton {
		e.atualizarUsuarioPorEmail(atualizado, emailChave);
	}
}
