package com.fabio.CRUD.DTO;

import com.fabio.CRUD.negocio.TypeUser;

public record UsuarioDTO (String nome,String email,String senha,TypeUser tipo,String dataCriacao,Boolean status){

}
