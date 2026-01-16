package com.fabio.CRUD.UI;

import java.util.Scanner;

import com.fabio.CRUD.Fachada.Fachada;
import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabio.CRUD.negocio.usuario.Usuario;

public class TelaLogin {
	
	public static Usuario login() {
		
		Scanner scanf = new Scanner(System.in);
		
		String email,senha;
		
		
		
		System.out.println("-------------------------");
		System.out.println("|\tBEM-VINDO\t|");
		System.out.println("|     SISTEMA LOGIN\t|");
		System.out.println("-------------------------\n");
		
		System.out.println("\n\tPreencha os campos abaixo!");
		
		try {
			
			System.out.println("Email: ");
			email = scanf.next().toLowerCase();
			System.out.println("Senha: ");
			senha = scanf.next();
			
			return Fachada.instance().verificacaoLogin(email, senha);
			
		}
		catch(OperacaoDeUsuarioInvalidoException e) {
			System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
		}
		catch(ErroNaEntradaSaidaExcepiton e) {
			System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
		}
		
		
		return null;
		
	}
	
}
