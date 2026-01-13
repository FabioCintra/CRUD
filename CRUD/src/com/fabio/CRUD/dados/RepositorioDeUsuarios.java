package com.fabio.CRUD.dados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fabio.CRUD.dados.execeptions.FalhaAoLerArquivoException;
import com.fabio.CRUD.dados.execeptions.ErroAoSalvarNoArquivoExcepiton;
import com.fabio.CRUD.negocio.InterfaceDados;
import com.fabio.CRUD.negocio.Usuario;
import com.fabio.CRUD.negocio.exceptions.ListaDeUsuariosVaziaException;

public class RepositorioDeUsuarios implements InterfaceDados{
	
	@Override
	public void salvarUser(Usuario user) throws ErroAoSalvarNoArquivoExcepiton{
		
		try(ObjectOutputStream salvar = new ObjectOutputStream(new FileOutputStream("BancoDeUsuarios.bin"))){
			Map<String,Usuario> mapUser = new HashMap<>();
			Optional<Map<String,Usuario>> listaUser = lendoUsurarios();
			
			/*
			 * Se existir o Map,
			 * Passa ele pra outra variavel e adicona o novo usuario nela
			 * Depois salva no arquivo!
			 * OBS: Substitui o arquivo por completo!
			 */
			
			if(listaUser.isPresent()) {
				mapUser = listaUser.get();
				mapUser.put(user.getEmail(), user);
				salvar.writeObject(mapUser);
			}
			/*
			 * Se nao existir,
			 * Eh feita a primeira insercao
			 * Por fim salva no arquivo!
			 */
			else {
				mapUser.put(user.getEmail(), user);
				salvar.writeObject(mapUser);
			}
		}
		catch(Exception e) {
			throw new ErroAoSalvarNoArquivoExcepiton();
		}
	}
	
	@Override
	/*
	 * Funcao responsavel por ler do arquivo o HashMap contendo todos os usuarios cadastrados
	 * Caso encontre o arquivo vazio retorna 'null' caso nao retorna ele
	 * 
	 * Se nao achar o arquivo lanca uma execessao
	 */
	public  Optional<Map<String,Usuario>>lendoUsurarios() throws FalhaAoLerArquivoException{
		
		Map<String,Usuario> listaUsuarios = new HashMap<>();
		
		try(ObjectInputStream leitor = new ObjectInputStream(new FileInputStream("BancoDeUsuarios.bin"))){
			Object obj = leitor.readObject();
			
			listaUsuarios = (Map<String, Usuario>) obj ;
			
			return (listaUsuarios == null) ? Optional.empty() : Optional.of(listaUsuarios);
		}
		catch(Exception e) {
			throw new FalhaAoLerArquivoException();
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
	public Optional<Usuario> buscaPorEmail(String email) throws ListaDeUsuariosVaziaException, FalhaAoLerArquivoException{
		
		Usuario userBuscado;
		Optional<Map<String,Usuario>> usuarios = lendoUsurarios();
		Map<String,Usuario> mapUser = new HashMap<>();
		
		
		if(!usuarios.isPresent()) {
			throw new ListaDeUsuariosVaziaException();
		}
		
		mapUser = usuarios.get();
		userBuscado = mapUser.get(email);
		
		if(userBuscado == null) {
			return Optional.empty();
		}
		else {
			return Optional.of(userBuscado);
		}
		
		
	}
	
}
