package com.fabio.CRUD.DTO;

import com.fabio.CRUD.negocio.eventos.Acao;
import com.fabio.CRUD.negocio.exceptions.ErroEventoException;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabio.CRUD.negocio.validacao.basicas.ValidaEmail;

public record EventoDTO(
String data,
String hora,
String autorEmail,
String alvoEmail,
Acao acao,
Boolean sucesso) {
	
	public  EventoDTO validacao() throws OperacaoDeUsuarioInvalidoException, ErroEventoException{
		
		//Validando email
		ValidaEmail.validarEmail(alvoEmail);
		ValidaEmail.validarEmail(autorEmail);
		
		
		if(acao == null || sucesso == null) {
			throw new ErroEventoException("Nao pode ter campos nulos!");
		}
		
		return this;
		
	}
	
}
