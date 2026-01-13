package com.fabio.CRUD.Fachada;


import com.fabio.CRUD.DTO.UsuarioDTO;
import com.fabio.CRUD.dados.RepositorioDeUsuarios;
import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.negocio.CodigoErroDTO;
import com.fabio.CRUD.negocio.InterfaceDados;
import com.fabio.CRUD.negocio.Usuario;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabioCRUD.Controller.ControladorUsuario;

public class Fachada {
	
	/*
	 * Singleton = var que tem como objetivo garantir que fachada so seja instanciada uma vez
	 */
	
	private static Fachada flag = null; 
	
	private Fachada() {}
	
	public static Fachada instance() {
		
		if(flag == null) {
			flag = new Fachada();
		}
		
		return flag;
	}
	
	
	/* 
	 * Variaveis da classe
	 */
	private InterfaceDados implementacao = new RepositorioDeUsuarios();
	
	/*
	 * Funcao responsavel por fazer a primeira chamadas as demais camadas, afim de gerar um usuario
	 */
	public void criandoUser(UsuarioDTO user) throws ErroNaEntradaSaidaExcepiton, OperacaoDeUsuarioInvalidoException{
		
		/*
		 * Essa classe pertence a camada de negocio e serve para informa a UI
		 * Se a operacao foi um sucesso, caso nao, informa qual erro foi acometido!
		 */
			
		ControladorUsuario.intance().criarUsuario(user, implementacao);
		
		
	}
	
	/*
	 * Serve para validar todos os emails que necessitem dew validacao
	 */
	
	public Fachada validandoEmail(String email) throws ErroNaEntradaSaidaExcepiton, OperacaoDeUsuarioInvalidoException {
		
		ControladorUsuario.intance().verificaEmail(email);
		
		return this;
	}
	
	/*
	 * Serve para validar todos os nomes que necessitem de validacao
	 * 
	 * Usado atualmente na atualizacaod e usuario
	 */
	public void verificandoNome(String nome) throws OperacaoDeUsuarioInvalidoException {
		ControladorUsuario.intance().verificaNome(nome);
	}
	
	/*
	 * Serve para validar todos os senhas que necessitem de validacao
	 * 
	 * Usado atualmente na atualizacaod e usuario
	 */
	public void verificandoSenha(String senha) throws OperacaoDeUsuarioInvalidoException  {
		ControladorUsuario.intance().verificaSenha(senha);
	}
	
	
	/*
	 * Serve para buscar um usuario com base no email recebido no UI
	 */
	public Usuario buscarUsuarioPorEmail(String email) throws ErroNaEntradaSaidaExcepiton {
		Usuario user = ControladorUsuario.intance().buscarUsuario(email, implementacao);
		
		return user;
	}
	
	
	/*
	 * Serve para atualizar algum dado de um usuario ja existente!
	 */
	public void atualizacaoDeUser(Usuario userNovo, String emailChave) throws ErroNaEntradaSaidaExcepiton {
		ControladorUsuario.intance().atualizarUser(userNovo, emailChave, implementacao);
	}
	
	public void deletarUser(String email) throws ErroNaEntradaSaidaExcepiton {
		ControladorUsuario.intance().deletarUsuario(email, implementacao);
	}
}	
