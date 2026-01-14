package com.fabio.CRUD.Controller;

import com.fabio.CRUD.DTO.UsuarioDTO;
import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabio.CRUD.negocio.usuario.AtualizarUsuario;
import com.fabio.CRUD.negocio.usuario.BuscarUsuario;
import com.fabio.CRUD.negocio.usuario.CriarUsuario;
import com.fabio.CRUD.negocio.usuario.DeletarUsuario;
import com.fabio.CRUD.negocio.usuario.InterfaceDados;
import com.fabio.CRUD.negocio.usuario.Usuario;
import com.fabio.CRUD.negocio.validacao.basicas.ValidaEmail;
import com.fabio.CRUD.negocio.validacao.basicas.ValidarNome;
import com.fabio.CRUD.negocio.validacao.basicas.ValidarSenha;

public class ControladorUsuario {
	/*
	 * 
	 * Singleton para a criar o Controller de Usuarios
	 * 
	 */

	private static ControladorUsuario controllerUsers = null;

	private ControladorUsuario() {
	};

	public static ControladorUsuario intance() {

		if (controllerUsers == null) {
			controllerUsers = new ControladorUsuario();
		}

		return controllerUsers;
	}

	public void criarUsuario(UsuarioDTO user, InterfaceDados e)
			throws ErroNaEntradaSaidaExcepiton, OperacaoDeUsuarioInvalidoException {

		CriarUsuario criador = new CriarUsuario(e);
		criador.criarUsuario(user);

	}

	public void verificaEmail(String email) throws ErroNaEntradaSaidaExcepiton, OperacaoDeUsuarioInvalidoException {
		ValidaEmail.validarEmail(email);

	}

	public void verificaNome(String nome) throws OperacaoDeUsuarioInvalidoException {
		ValidarNome.validarNome(nome);
	}

	public void verificaSenha(String senha) throws OperacaoDeUsuarioInvalidoException {
		ValidarSenha.validarSenha(senha);
	}

	public Usuario buscarUsuario(String email, InterfaceDados e) throws ErroNaEntradaSaidaExcepiton {
		BuscarUsuario busca = new BuscarUsuario();
		Usuario user = busca.buscarUsuario(email, e);

		return user;
	}

	public void atualizarUser(Usuario atualizado, String emailChave, InterfaceDados inter)
			throws ErroNaEntradaSaidaExcepiton {
		AtualizarUsuario.atualizandoUsuario(emailChave, atualizado, inter);
	}

	public void deletarUsuario(String email, InterfaceDados implementada) throws ErroNaEntradaSaidaExcepiton {
		DeletarUsuario.deletarUsuarioPorEmail(email, implementada);
	}
}
