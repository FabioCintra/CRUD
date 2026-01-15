package com.fabio.CRUD.dados.usuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.negocio.usuario.CodigoErroDTO;
import com.fabio.CRUD.negocio.usuario.InterfaceDados;
import com.fabio.CRUD.negocio.usuario.Usuario;


public class RepositorioDeUsuarios implements InterfaceDados{
	
	@Override
	public void salvarUser(Usuario user) throws  ErroNaEntradaSaidaExcepiton{
		
		/*
		 * Se existir o Map,
		 * Passa ele pra outra variavel e adicona o novo usuario nela
		 * Depois salva no arquivo!
		 * OBS: Substitui o arquivo por completo!
		 */
		
		Map<String,Usuario> mapUser = lendoUsurarios().orElse(new HashMap<>());
		mapUser.put(user.getEmail(), user);
		
		//Salvando o novo Map de usuario!
		salvamentoDeHashMap(mapUser);
	}
	
	@Override
	/*
	 * Funcao responsavel por ler do arquivo o HashMap contendo todos os usuarios cadastrados
	 * Caso encontre o arquivo vazio retorna 'null' caso nao retorna ele
	 * 
	 * Se nao achar o arquivo lanca uma execessao
	 */
	public  Optional<Map<String,Usuario>>lendoUsurarios() throws ErroNaEntradaSaidaExcepiton{
		
		Map<String,Usuario> listaUsuarios;
			
		try(ObjectInputStream leitor = new ObjectInputStream(new FileInputStream("./data/BancoDeUsuarios.bin"))){
			Object obj = leitor.readObject();
				
			listaUsuarios = (Map<String, Usuario>) obj ;
				
			return (listaUsuarios == null) ? Optional.empty() : Optional.of(listaUsuarios);
		}
		catch(FileNotFoundException e) {
			return Optional.empty();
		}
		catch(Exception e) {
			throw new ErroNaEntradaSaidaExcepiton(CodigoErroDTO.ERRO_AO_LER_ARQUIVO, "Erro na leitura de dados!");
		}
	}
			

	@Override
	/*
	 * Vai verificar na banco de dados se existe a pessoa a ser buscada
	 * 
	 * Se existir, retorna a pessoa
	 * Se nao retorna 'null'
	 * 
	 * OBS: Caso o Map estiver vazio ele ira retornar uma execessao alertando que o banco de dados de users esta vazio
	 */
	public Optional<Usuario> buscaPorEmail(String email) throws ErroNaEntradaSaidaExcepiton {
		
		Usuario userBuscado;
		Map<String,Usuario> mapUser = lendoUsurarios().orElse(new HashMap<>());
		
			
		userBuscado = mapUser.get(email);
			
		if(userBuscado == null) {
			return Optional.empty();
		}
		else {
			return Optional.of(userBuscado);
		}
		
	}

	@Override
	public void atualizarUsuarioPorEmail(Usuario usuarioAtualizado, String emailChave) throws ErroNaEntradaSaidaExcepiton {
		
		Map<String,Usuario> mapUser = lendoUsurarios().orElse(null);
		
		//o if pode ser que suma depois
		
		
		mapUser.remove(emailChave);
		mapUser.put(usuarioAtualizado.getEmail(), usuarioAtualizado);
		
		//Salvando o novo Map de usuario!
		salvamentoDeHashMap(mapUser);
		
	}
	
	@Override
	public void deletarUsuarioPeloEmail(String email) throws ErroNaEntradaSaidaExcepiton {
		Map<String,Usuario> mapUser = lendoUsurarios().orElse(null);
		
		//removendo o usuario!
		mapUser.remove(email);
		
		//Salvando o novo Map de usuario!
		salvamentoDeHashMap(mapUser);
		
	}
	
	private void salvamentoDeHashMap(Map<String,Usuario> mapUser) throws ErroNaEntradaSaidaExcepiton {
		try(ObjectOutputStream salvar = new ObjectOutputStream(new FileOutputStream("./data/BancoDeUsuarios.bin "))){
			
			salvar.writeObject(mapUser);
			
		}
		catch(Exception e) {
			throw new ErroNaEntradaSaidaExcepiton(CodigoErroDTO.ERRO_AO_SALVAR_ARQUIVO, "Erro no salvamento de dados!");
		}
	}
	
}
