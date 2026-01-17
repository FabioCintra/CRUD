package com.fabio.CRUD.negocio.eventos;

import java.util.ArrayList;
import java.util.List;

import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.negocio.exceptions.ErroEventoException;

public class ExibirEvento {
	
	public static void exibirEvento(BancoDeEvento banco) throws ErroNaEntradaSaidaExcepiton, ErroEventoException {
		List<Evento> eventosTotais = banco.lerEventos().orElse(new ArrayList<>());
		
		if(!(eventosTotais.isEmpty())) {
			System.out.println("------------HISTORICO-------------");
			for(Evento i : eventosTotais) {
				i.imprimir();
			}
		}
		else {
			throw new ErroEventoException("Nao ha eventos cadastrados no momento!");
		}
	}
}
