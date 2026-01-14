package com.fabio.CRUD.negocio.usuario;

public class GeradorId {
	private static int id = 0;
	
	
	public static int idUsuario() {
		return ++id;
	}
}
