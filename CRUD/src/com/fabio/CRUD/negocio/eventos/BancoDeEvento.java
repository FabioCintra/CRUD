package com.fabio.CRUD.negocio.eventos;

import java.util.List;
import java.util.Optional;

import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;

public interface BancoDeEvento {
	public abstract void salvarEvento(Evento evento) throws ErroNaEntradaSaidaExcepiton,ErroNaEntradaSaidaExcepiton;
	public abstract Optional<List<Evento>> lerEventos() throws ErroNaEntradaSaidaExcepiton;
}
