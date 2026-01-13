package com.fabio.CRUD.DTO;

import com.fabio.CRUD.negocio.CodigoErroDTO;

public record ResultadoDeCriarUsuario (Boolean resultado, CodigoErroDTO e, String mensagem){

}
