package com.fabio.CRUD.negocio.eventos;

import java.io.Serializable;

import com.fabio.CRUD.DTO.EventoDTO;

public class Evento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idEvento;
	private String data;
	private String hora;
	private String autorEmail;
	private String alvoEmail;
	private String detalhes;
	private Acao acao;
	private Boolean sucesso;
	
	
	public Evento(EventoDTO evento) {
		this.idEvento = GeradorIdEvento.getId();
		this.acao = evento.acao();
		this.data = evento.data();
		this.hora = evento.hora();
		this.autorEmail = evento.autorEmail();
		this.alvoEmail = evento.alvoEmail();
		this.detalhes = Acao.mensagemAcao(evento.acao());
		this.sucesso = evento.sucesso();
	}
	
	public void imprimir() {
		
		System.out.println("\t|Id: " + this.idEvento + "|\n\t|Autor: " + this.autorEmail + "|\n\t|Alvo: " + this.alvoEmail + "|\n\t|Data : " + this.data + 
						   "|\n\t|Hora: " + this.hora + "|\n\t|Detalhes: " + this.detalhes + "|");
		if(this.sucesso) {
			System.out.print("\t|Resultado: Sucesso|\n"); 
		}
		else {
			System.out.println("\t|Resultado: Falha|\n");
		}
		
		System.out.println("--------------------------------");
		System.out.println();
	}

	public int getIdEvento() {
		return idEvento;
	}

	public String getData() {
		return data;
	}

	public String getHora() {
		return hora;
	}

	public String getAutorEmail() {
		return autorEmail;
	}

	public String getAlvoEmail() {
		return alvoEmail;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public Acao getAcao() {
		return acao;
	}

	public Boolean getSucesso() {
		return sucesso;
	}
	
	
	
}
