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
	
	//essa funcao ela tem como funcao criar um usuario!
	public void criarUsuario(UsuarioDTO user, InterfaceDados e)
			throws ErroNaEntradaSaidaExcepiton, OperacaoDeUsuarioInvalidoException {

		CriarUsuario criador = new CriarUsuario(e);
		criador.criarUsuario(user);

	}
	
	//essa funcao ela tem como objetivo verificar se um email esta dentro das normas do programa!
	public void verificaEmail(String email) throws ErroNaEntradaSaidaExcepiton, OperacaoDeUsuarioInvalidoException {
		ValidaEmail.validarEmail(email);

	}
	
	//essa funcao ela tem como objetivo verificar se o nome do usuario esta dentro das normas do programa!
	public void verificaNome(String nome) throws OperacaoDeUsuarioInvalidoException {
		ValidarNome.validarNome(nome);
	}

	//essa funcao ela tem como objetivo verificar se a senha do usuario esta dentro das normas do programa!
	public void verificaSenha(String senha) throws OperacaoDeUsuarioInvalidoException {
		ValidarSenha.validarSenha(senha);
	}
	
	/*
	 * essa funcao ela tem como objetivo buscar um usuario no banco de dados por meio da sua chave de acesso(email)!
	 * 
	 * E como pode ocorrer de encontrar ele ou nao, se encontrar retorna ele
	 * 
	 * Se nao retorna um usuario nulo
	 */
	public Usuario buscarUsuario(String email, InterfaceDados e) throws ErroNaEntradaSaidaExcepiton {

		Usuario user = new BuscarUsuario().buscarUsuario(email, e);

		return user;
	}
	
	/*
	 * Essa funcao tem como intuito atualizar um usuario!
	 */
	public void atualizarUser(Usuario atualizado, String emailChave, InterfaceDados inter)
			throws ErroNaEntradaSaidaExcepiton {
		AtualizarUsuario.atualizandoUsuario(emailChave, atualizado, inter);
	}
	
	/*
	 * Essa funcao tem como intuito deletar um usuario!
	 */
	public void deletarUsuario(String email, InterfaceDados implementada) throws ErroNaEntradaSaidaExcepiton {
		DeletarUsuario.deletarUsuarioPorEmail(email, implementada);
	}
}
