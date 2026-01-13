package com.fabio.CRUD.negocio;

import java.util.Map;
import java.util.Optional;

import com.fabio.CRUD.dados.execeptions.FalhaAoLerArquivoException;
import com.fabio.CRUD.dados.execeptions.ErroAoSalvarNoArquivoExcepiton;
import com.fabio.CRUD.negocio.exceptions.ListaDeUsuariosVaziaException;

/*
 * serve como uma ponte entre a camada de dados e a de negocios
 * Nao permitindo um contato direto entre elas!
*/
public interface InterfaceDados {
	
	public abstract void salvarUser(Usuario user) throws ErroAoSalvarNoArquivoExcepiton;
	Optional<Map<String,Usuario>>lendoUsurarios()throws FalhaAoLerArquivoException;
	Optional<Usuario> buscaPorEmail(String email)throws  FalhaAoLerArquivoException, ListaDeUsuariosVaziaException;
	
}
