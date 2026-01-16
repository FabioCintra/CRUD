package com.fabio.CRUD.config;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.fabio.CRUD.DTO.UsuarioDTO;
import com.fabio.CRUD.Fachada.Fachada;
import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.dados.usuario.RepositorioDeUsuarios;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabio.CRUD.negocio.usuario.InterfaceDados;
import com.fabio.CRUD.negocio.usuario.TypeUser;
import com.fabio.CRUD.negocio.usuario.Usuario;

/*
 *Classe que obtem dados de config, para poder inicializar o sistema!
 *
 * 
 * Pode tambem inicializar outras classe como a Fachada
 */
public class BootStrapIncialization {
	
	
	private static File file;
	
	
	public static void init(){
		try {
			if(file == null) {
				file = new File(AppConfig.get("db.path"));
			}
			
			//se o arquivo BancoUsuario.bin nao existir
			if(!file.exists()) {
				// criando o arquivo BancoUsuario.bin e cadastrando o primeiro ADM
				BootStrapIncialization.criandoPrimeiroAdm();
			}
			else {
				InterfaceDados implementada = new RepositorioDeUsuarios();
				Map<String,Usuario> users = implementada.lendoUsurarios().orElse(new HashMap<>());
				
				//Se o admin principal nao estiver cadastrado!
				if(users.get(AppConfig.get("admin.email")) == null) {
					BootStrapIncialization.criandoPrimeiroAdm();
				}
			}
		}
		catch(OperacaoDeUsuarioInvalidoException e) {
			System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
		}
		catch(ErroNaEntradaSaidaExcepiton e) {
			System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
		}
		
		
	}
	
	private static void criandoPrimeiroAdm() throws ErroNaEntradaSaidaExcepiton, OperacaoDeUsuarioInvalidoException {
		//Alocando a data atual de cadastramento
		LocalDate agora = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataCriacao = agora.format(formato);
		
		UsuarioDTO user = new UsuarioDTO(AppConfig.get("admin.nome"),AppConfig.get("admin.email"), AppConfig.get("admin.senha"),TypeUser.ADMIN,dataCriacao,true);
		Fachada.instance().criandoUser(user);
	}
}	
