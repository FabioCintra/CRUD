package com.fabio.CRUD.negocio.eventos;

import java.util.List;
import java.util.Optional;

import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;

/*
 * Interface que quando implementada faz a ligacao entre a pasta de negocio com a pasta de dados
 * permitindo que se conectem mas sem contato direto!
 */
public interface BancoDeEvento {
	public abstract void salvarEvento(Evento evento) throws ErroNaEntradaSaidaExcepiton,ErroNaEntradaSaidaExcepiton;
	public abstract Optional<List<Evento>> lerEventos() throws ErroNaEntradaSaidaExcepiton;
}
