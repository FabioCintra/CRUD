package com.fabio.CRUD.negocio.eventos;

public class GeradorIdEvento {
	private static int idEvento = 0;
	
	public static int getId() {
		return ++idEvento;
	}
}
