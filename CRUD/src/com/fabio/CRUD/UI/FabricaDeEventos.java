package com.fabio.CRUD.UI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fabio.CRUD.DTO.EventoDTO;
import com.fabio.CRUD.negocio.eventos.Acao;

public class FabricaDeEventos {
	
	
	public static EventoDTO gerandoEventoDTO(String alterador, String alterado, Acao acao, Boolean sucesso) {
		
		//Alocando a data atual 
		String dataCriacao = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		//Alocando a data atual 
		String horaCriacao = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		
		return new EventoDTO(dataCriacao,horaCriacao,alterador,alterado,acao,sucesso);
		
	}
}
