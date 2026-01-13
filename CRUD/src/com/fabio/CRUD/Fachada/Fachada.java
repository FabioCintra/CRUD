package com.fabio.CRUD.Fachada;

import com.fabio.CRUD.DTO.UsuarioDTO;
import com.fabio.CRUD.dados.execeptions.FalhaAoLerArquivoException;
import com.fabio.CRUD.negocio.exceptions.CampoNuloException;
import com.fabio.CRUD.negocio.exceptions.EmailJaCadastradoException;
import com.fabio.CRUD.negocio.exceptions.FormatoDoEmailErradoException;
import com.fabio.CRUD.negocio.exceptions.ListaDeUsuariosVaziaException;
import com.fabio.CRUD.negocio.exceptions.TamanhoDeNomeInvalidoException;
import com.fabio.CRUD.negocio.exceptions.TamanhoDeSenhaInvalidoException;
import com.fabio.CRUD.negocio.validacao.ValidandoUsuario;

public class Fachada {
	
	/*
	 * 
	 * Singleton = var que tem como objetivo garantir que fachada so seja instanciada uma vez
	 * 
	 */
	
	private static Fachada flag = null; 
	
	private Fachada() {}
	
	public Fachada instance() {
		
		if(flag == null) {
			return new Fachada();
		}
		
		return flag;
	}
	
	/*
	 * Funcao responsavel por fazer a primeira chamadas as demais camadas, afim de gerar um usuario
	 * 
	 * 
	 * Singleton abaixo serve para garantir que a validacao de user s[o vai ser instanciada uma vez
	 * pois nao a necessidade de ocorrer mais de uma instancia dela!
	 */
	
	private static ValidandoUsuario validacao = null;
	
	private ValidandoUsuario instanceValidador() {
		
		if(validacao == null) {
			return new ValidandoUsuario();
		}
		
		return validacao;
	}
	public void criarUsuario(UsuarioDTO user) throws ListaDeUsuariosVaziaException, FormatoDoEmailErradoException, CampoNuloException, FalhaAoLerArquivoException, 
	EmailJaCadastradoException, TamanhoDeSenhaInvalidoException, TamanhoDeNomeInvalidoException {
		ValidandoUsuario vUser = instanceValidador();
		
		if(vUser.validarUser(user)) {
			
		}
	}
}	
