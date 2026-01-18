package com.fabio.CRUD.UI;

import java.util.Scanner;

import com.fabio.CRUD.DTO.EventoDTO;
import com.fabio.CRUD.Fachada.Fachada;
import com.fabio.CRUD.config.BootStrapIncialization;
import com.fabio.CRUD.negocio.eventos.Acao;
import com.fabio.CRUD.negocio.usuario.Usuario;

public class Main {
	
	/*
	 * Aqui ficara todas as chamadas de funcoes necessarias para o programa funcionar
	 */
	public static void main(String[] args)  {
		/*
		 * Essa funcao eh responsavel por verificar se o admin principal esta cadastrado no programa ou se ja existe banco de dados
		 * 
		 * Se nao existir banco de dados de User, ele cria e ja adiciona o admin principal!
		 * 
		 * Se o BD USer existir mas o admin Principal nao estiver cadastrado, ele cadastra!
		 * 
		 * Se o admin exitir, essa funcao nao faz nada!
		 */
		BootStrapIncialization.init();
		
		/*
		 * A funcao abaixo eh a tela de login, local onde o usuario vai dizer seu email e senha
		 * 
		 * Se o email estiver cadastrado e a senha for igual a senha cadastrada nesse email
		 * Eh retornado o tipo de usuario dessa conta!
		 */
		Usuario user = null; 
		String opcao = "";
		
		while(user == null) {
			
			/*
			 * Vai tentar logar no usuario atraves das informacoes dadas em TelaLogin.Login()
			 * 
			 * Mas se retornar nulo, vai registrar um evento de erro de login!
			 * 
			 * Mas se nao, sai do While e vai direto pra tela do sistema
			 * 
			 */
			user = TelaLogin.login();
			if(user == null) {
				
				//cadastrando evento
				System.out.println("\n\u001B[31m[ERRO]: " + "User not found" + "\n\u001B[0m");
				EventoDTO evento = null;
				
				evento = FabricaDeEventos.gerandoEventoDTO(null, null, Acao.LOGIN_ERRO, false);
				Fachada.instance().criarEvento(evento);
				
				/*
				 * Pergunta ao usuario se deseja tentar novamente ou encerrar o programa!
				 */
				System.out.println("1 - Tentar novamente\n2 - Sair");
				opcao = Validacao.validarOpcaoMenuErro(new Scanner(System.in).next());
				
				if(opcao.equals("2")) {break;}
			}
			
		}
		
		
		/*
		 * A chamada a baixo eh onde todo o sistema roda com todas suas funcionabilidades de CRUD
		 * 
		 * C: Criar
		 * R: Ler
		 * U: Atualizar
		 * D: Deletar
		 * 
		 * Alem disso registra todas as acoes que alguem fizer no programa mesmo que tal acao nao tenha exito
		 * 
		 * So entra se o usuario nao escolher sair do programa, caso tenha errado as credenciaisadm
		 */
		if(!(opcao.equals("2"))){Sistema.iniciar(user);}
		
	}

}
