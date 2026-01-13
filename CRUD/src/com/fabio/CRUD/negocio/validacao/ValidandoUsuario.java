package com.fabio.CRUD.negocio.validacao;



import com.fabio.CRUD.DTO.UsuarioDTO;
import com.fabio.CRUD.dados.execeptions.FalhaAoLerArquivoException;
import com.fabio.CRUD.negocio.exceptions.CampoNuloException;
import com.fabio.CRUD.negocio.exceptions.EmailJaCadastradoException;
import com.fabio.CRUD.negocio.exceptions.FormatoDoEmailErradoException;
import com.fabio.CRUD.negocio.exceptions.ListaDeUsuariosVaziaException;
import com.fabio.CRUD.negocio.exceptions.TamanhoDeNomeInvalidoException;
import com.fabio.CRUD.negocio.exceptions.TamanhoDeSenhaInvalidoException;
import com.fabio.CRUD.negocio.validacao.basicas.ValidaEmail;
import com.fabio.CRUD.negocio.validacao.basicas.ValidarNome;
import com.fabio.CRUD.negocio.validacao.basicas.ValidarSenha;

public class ValidandoUsuario {
	
	public Boolean validarUser(UsuarioDTO user) throws FormatoDoEmailErradoException, CampoNuloException, FalhaAoLerArquivoException, 
	ListaDeUsuariosVaziaException, EmailJaCadastradoException, TamanhoDeSenhaInvalidoException, TamanhoDeNomeInvalidoException {
		
		if(ValidaEmail.validarEmail(user.email()) && ValidarSenha.validarSenha(user.senha()) && ValidarNome.validarNome(user.nome())) {
			return true;
		}
		
		return false;
	}
}
