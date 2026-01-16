package com.fabio.CRUD.UI;

import java.util.Scanner;

import com.fabio.CRUD.negocio.usuario.TypeUser;


public class Validacao {
	
	/*
	 * Valida se a opcao de acao do menu foi escolhida adequadamente
	 */
	public static String validarOpcaoMenu(String opcao) {
		String regex = "^[0-6]{1}$";
		
		while(!opcao.matches(regex)){
			Scanner scanf = new Scanner(System.in);
			System.err.println("Opcao invalida! Tente novamente:");
			opcao = scanf.next();
		}
		
		return opcao;
	}
	
	public static String validarOpcaoMenuErro(String opcao) {
		String regex = "^[1-2]{1}$";
		
		while(!opcao.matches(regex)){
			Scanner scanf = new Scanner(System.in);
			System.err.println("Opcao invalida! Tente novamente:");
			opcao = scanf.next();
		}
		
		return opcao;
	}
	/*
	 * esta funcao tem como objetivo determinar o tipo de usuario
	 * que vai se tornar o "user" cadastrado!
	 */
	public static TypeUser tipoDeUsuario(String tipoUser) {
	
		while(!(tipoUser.toLowerCase().equals("admin")) && !(tipoUser.toLowerCase().equals("comum")) ) {
			Scanner scanf = new Scanner(System.in);
			System.err.println("Tipo de usuario invalido! Tente novamente: ");
			tipoUser = scanf.next();
		}
		
		
		if(tipoUser.toLowerCase().equals("admin")) {
			return TypeUser.ADMIN;
		}
		else {
			return TypeUser.COMUM;
		}
		
	}
	
	
}
