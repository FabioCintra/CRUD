package com.fabio.CRUD.negocio.eventos;

import com.fabio.CRUD.DTO.EventoDTO;
import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;

public class CriarEvento {
	
	public static void criarEvento(EventoDTO evento, BancoDeEvento banco) throws ErroNaEntradaSaidaExcepiton {
		Evento novoEvento = new Evento(evento);
		banco.salvarEvento(novoEvento);
	}
}
