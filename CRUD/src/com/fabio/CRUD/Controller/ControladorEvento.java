package com.fabio.CRUD.Controller;

import com.fabio.CRUD.DTO.EventoDTO;
import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.negocio.eventos.BancoDeEvento;
import com.fabio.CRUD.negocio.eventos.CriarEvento;

public class ControladorEvento {
	
	
	//Singleton
	
	private static ControladorEvento controller = null;
	
	private ControladorEvento () {};
	
	public static ControladorEvento instance() {
		
		if(controller == null) {
			controller = new ControladorEvento();
		}
		
		return controller;
	}
	
	//funcao de criar evento
	public void criarEvento(EventoDTO evento, BancoDeEvento banco) throws ErroNaEntradaSaidaExcepiton {
		CriarEvento.criarEvento(evento, banco);
	}
}
