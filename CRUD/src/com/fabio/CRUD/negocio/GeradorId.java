package com.fabio.CRUD.negocio;

public class GeradorId {
	private static int id = 0;
	
	
	public static int idUsuario() {
		return ++id;
	}
}
