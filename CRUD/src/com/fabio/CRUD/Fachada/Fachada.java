package com.fabio.CRUD.Fachada;


import com.fabio.CRUD.Controller.ControladorEvento;
import com.fabio.CRUD.Controller.ControladorUsuario;
import com.fabio.CRUD.DTO.EventoDTO;
import com.fabio.CRUD.DTO.UsuarioDTO;
import com.fabio.CRUD.dados.eventos.RepositorioDeEventos;
import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.dados.usuario.RepositorioDeUsuarios;
import com.fabio.CRUD.negocio.eventos.BancoDeEvento;
import com.fabio.CRUD.negocio.exceptions.ErroEventoException;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabio.CRUD.negocio.usuario.CodigoErroDTO;
import com.fabio.CRUD.negocio.usuario.InterfaceDados;
import com.fabio.CRUD.negocio.usuario.TypeUser;
import com.fabio.CRUD.negocio.usuario.Usuario;


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
	private BancoDeEvento bancoEvento = new RepositorioDeEventos();
	
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
	
	/*
	 * Serve para deletar algum usuario ja existente!
	 */
	public void deletarUser(String email) throws ErroNaEntradaSaidaExcepiton {
		ControladorUsuario.intance().deletarUsuario(email, implementacao);
	}
	
	/*
	 * Serve para criar um evento!
	 * 
	 * Evento nada mais eh doque um registro de qualquer acao que for realizada no sistema
	 */
	public void criarEvento(EventoDTO evento) {
		try {
			ControladorEvento.instance().criarEvento(evento, bancoEvento);
		}
		catch (ErroNaEntradaSaidaExcepiton e) {
			System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
		}
		
	}
	
	public void exibirEventos() throws ErroNaEntradaSaidaExcepiton, ErroEventoException {
		ControladorEvento.instance().exibirEvento(bancoEvento);
	}
	
	
	/*
	 * Essa funcao vai verificar se as credenciais de loguin estao ok
	 * 
	 * E retornara o tipo de usuario que esta logando no sistema, se os dados estiverem ok!
	 * 
	 * Caso nao da excessao
	 */
	public Usuario verificacaoLogin(String email, String senha) throws ErroNaEntradaSaidaExcepiton, OperacaoDeUsuarioInvalidoException {
		
		//Verificando se o email e a senha estao num formato ok!
		validandoEmail(email);
		verificandoSenha(senha);
		
		/*
		 * Caso a validacao der certo, o usuario correspondente ao email digitado sera buscado!
		 * 
		 * Se encontrado, testara se a senha eh igual a informada
		 * 
		 * Caso seja, retornara o seu tipo de usuario!
		 * 
		 * Caso nao o encontre, da excessao!
		 */
		Usuario user = buscarUsuarioPorEmail(email);
		
		if(user == null) {
			throw new OperacaoDeUsuarioInvalidoException(CodigoErroDTO.USUARIO_NAO_ENCONTRADO, "Nao existe usuario com esse email!");
		}
		
		if(!(senha.equals(user.getSenha()))){
			throw new OperacaoDeUsuarioInvalidoException(CodigoErroDTO.SENHA_DIFERENTE, "Senha incorreta!");
		}
		
		return user;
	}
}	
