package com.fabio.CRUD.negocio.usuario;

import java.util.Map;
import java.util.Optional;

import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;

/*
 * serve como uma ponte entre a camada de dados e a de negocios
 * Nao permitindo um contato direto entre elas!
*/
public interface InterfaceDados {
	
	public abstract void salvarUser(Usuario user) throws ErroNaEntradaSaidaExcepiton;
	Optional<Map<String,Usuario>>lendoUsurarios()throws ErroNaEntradaSaidaExcepiton;
	Optional<Usuario> buscaPorEmail(String email)throws  ErroNaEntradaSaidaExcepiton;
	public abstract void atualizarUsuarioPorEmail(Usuario usuarioAtualizado,String emailChave)throws ErroNaEntradaSaidaExcepiton;
	public abstract void deletarUsuarioPeloEmail(String email) throws ErroNaEntradaSaidaExcepiton ;
	
	
}
