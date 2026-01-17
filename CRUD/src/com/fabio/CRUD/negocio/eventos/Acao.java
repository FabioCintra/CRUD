package com.fabio.CRUD.negocio.eventos;

public enum Acao {
	CADASTRAR_USUARIO_SUCESSO,CADASTRAR_USUARIO_ERRO,
	ATUALIZAR_USUARIO_SUCESSO,ATUALIZAR_USUARIO_ERRO,
	REMOVER_USUARIO_SUCESSO,REMOVER_USUARIO_ERRO,
	LOGIN_SUCESSO, LOGIN_ERRO,EXIBINDO_INFO,
	BUSCAR_USUARIO_ERRO,BUSCAR_USUARIO_SUCESSO,
	LOGOUT;
	
	public static String mensagemAcao(Acao tipo) {
		if(tipo == Acao.ATUALIZAR_USUARIO_ERRO) {
			return "Erro ao atualizar usuario";
		}
		if(tipo == Acao.ATUALIZAR_USUARIO_SUCESSO) {
			return "Usuario atualizado!";
		}
		if(tipo == Acao.CADASTRAR_USUARIO_ERRO) {
			return "Erro ao cadastrar usuario";
		}
		if(tipo == Acao.CADASTRAR_USUARIO_SUCESSO) {
			return "Usuario cadastrado!";
		}
		if(tipo == Acao.LOGIN_ERRO) {
			return "Erro ao logar usuario";
		}
		if(tipo == Acao.LOGOUT) {
			return "Usuario deslogado";
		}
		if(tipo == Acao.REMOVER_USUARIO_ERRO) {
			return "Erro ao remover usuario";
		}
		if(tipo == Acao.REMOVER_USUARIO_SUCESSO) {
			return "Usuario removido!";
		}
		if(tipo == Acao.ATUALIZAR_USUARIO_ERRO) {
			return "Erro ao atualizar usuario";
		}
		if(tipo == Acao.EXIBINDO_INFO) {
			return "Usuario verificou suas informacoes";
		}
		if(tipo == Acao.BUSCAR_USUARIO_ERRO) {
			return "Erro ao buscar usuario";
		}
		if(tipo == Acao.BUSCAR_USUARIO_SUCESSO) {
			return "Sucesso ao buscar usuario";
		}
		return "Usuario logou!";
	}
}
