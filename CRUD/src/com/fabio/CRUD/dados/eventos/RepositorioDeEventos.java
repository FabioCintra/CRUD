package com.fabio.CRUD.dados.eventos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.negocio.eventos.BancoDeEvento;
import com.fabio.CRUD.negocio.eventos.Evento;
import com.fabio.CRUD.negocio.usuario.CodigoErroDTO;

public class RepositorioDeEventos implements BancoDeEvento{
	
	
	private List<Evento> eventos;
	
	@Override
	public void salvarEvento(Evento evento) throws ErroNaEntradaSaidaExcepiton,ErroNaEntradaSaidaExcepiton {
		
		/*
		 * Le o banco de dados inicialmente, se ele nao existir cria um novo ArrayList<>() 
		 * mas se existir ele somente pega
		 * 
		 * Depois adiciona na lista o novo evento
		 * 
		 * E salva no arquivo!
		 */
		eventos = lerEventos().orElse(new ArrayList<>());
		
		try(ObjectOutputStream salvar = new ObjectOutputStream(new FileOutputStream("./data/BancoEventos"))){
			
			eventos.add(evento);
			
			salvar.writeObject(eventos);
		}
		catch(Exception e) {
			throw new ErroNaEntradaSaidaExcepiton(CodigoErroDTO.ERRO_AO_SALVAR_ARQUIVO, "Erro no salvamento de dados!");
		}
	}

	@Override
	public Optional<List<Evento>> lerEventos() throws ErroNaEntradaSaidaExcepiton {
		
		try(ObjectInputStream ler = new ObjectInputStream(new FileInputStream("./data/BancoEventos"))){
			
			Object obj = ler.readObject();
			
			eventos = (List<Evento>) obj;
			
			return (eventos.isEmpty()) ? Optional.empty() : Optional.of(eventos);
			
		}
		catch(Exception e){
			throw new ErroNaEntradaSaidaExcepiton(CodigoErroDTO.ERRO_AO_LER_ARQUIVO, "Erro na leitura de dados!");
		}
	}
	
}
