package com.fabio.CRUD.negocio;

import com.fabio.CRUD.DTO.UsuarioDTO;
import com.fabio.CRUD.dados.execeptions.ErroAoSalvarNoArquivoExcepiton;
import com.fabio.CRUD.negocio.exceptions.UsuarioInvalidoException;

public class CriarUsuario {
	private InterfaceDados salvar;
	
	public CriarUsuario(InterfaceDados implementacao) {
		this.salvar = implementacao;
	}
	
	public void criarUsuario(UsuarioDTO user) throws UsuarioInvalidoException, ErroAoSalvarNoArquivoExcepiton {
		Usuario usuario = new Usuario(user);
		salvar.salvarUser(usuario);
	}
}
