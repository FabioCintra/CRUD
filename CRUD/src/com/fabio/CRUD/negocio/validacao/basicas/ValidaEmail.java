package com.fabio.CRUD.negocio.validacao.basicas;


import java.util.Optional;

import com.fabio.CRUD.dados.RepositorioDeUsuarios;
import com.fabio.CRUD.dados.execeptions.FalhaAoLerArquivoException;
import com.fabio.CRUD.negocio.InterfaceDados;
import com.fabio.CRUD.negocio.Usuario;
import com.fabio.CRUD.negocio.exceptions.CampoNuloException;
import com.fabio.CRUD.negocio.exceptions.EmailJaCadastradoException;
import com.fabio.CRUD.negocio.exceptions.FormatoDoEmailErradoException;
import com.fabio.CRUD.negocio.exceptions.ListaDeUsuariosVaziaException;


public class ValidaEmail {
	public static Boolean validarEmail(String email) throws FormatoDoEmailErradoException, CampoNuloException, EmailJaCadastradoException, ListaDeUsuariosVaziaException, FalhaAoLerArquivoException{
		
		String regex = "^[a-zA-z0-9]{5,20}@gmail\\.com";
		InterfaceDados dados = new RepositorioDeUsuarios();
		Optional<Usuario>user = dados.buscaPorEmail(email);
		
		if(email.isEmpty()) {
			throw new CampoNuloException();
		}
		if(!email.matches(regex)) {
			throw new FormatoDoEmailErradoException();
		}
		if(user.isPresent()) {
			throw new EmailJaCadastradoException();
		}
		
		
		return true;
		
	}
	
}
