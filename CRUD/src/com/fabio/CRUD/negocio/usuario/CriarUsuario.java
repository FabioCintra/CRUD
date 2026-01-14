package com.fabio.CRUD.negocio.usuario;

import com.fabio.CRUD.DTO.UsuarioDTO;
import com.fabio.CRUD.dados.RepositorioDeUsuarios;
import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;

public class CriarUsuario {
	private InterfaceDados salvar;
	
	public CriarUsuario(InterfaceDados implementacao) {
		this.salvar = implementacao;
	}
	
	public void criarUsuario(UsuarioDTO user) throws ErroNaEntradaSaidaExcepiton, OperacaoDeUsuarioInvalidoException {
		
		/*
		 * Checa se o email ja esta em uso
		 */
		InterfaceDados dados = new RepositorioDeUsuarios();
		Usuario usuario = dados.buscaPorEmail(user.email()).orElse(null);
		
		
		if(usuario != null &&  usuario.getStatus() == true) {
			throw new OperacaoDeUsuarioInvalidoException(CodigoErroDTO.EMAIL_JA_CADASTRADO, "Email escolhido ja esta em uso!");
		}
		
		/*
		 * Cria o usuario
		 */
		Usuario usuarioNovo = new Usuario(user);
		salvar.salvarUser(usuarioNovo);
	}
}
