package com.fabio.CRUD.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fabio.CRUD.config.exception.ErroConfigException;

public class AppConfig{
	
	/*
	 * Serve para ler uma linha de bytes em um arquivo e identificar se o nome procurado
	 * existe e o que ele representa!
	 * 
	 * Isso [e estabelicido por um sinal de igual "=" ou dois pontos ":"
	 */
	private static Properties props = new Properties();
	
	
	/*
	 * Esse static, nada mais eh do que um meio de que tudo que estiver dentro dele vaio ser 
	 * executado assim que a classe for chamada
	 */
	static {
		
		
		/*
		 * InputStream = eh basicamente um leitor de bytes de um arquivo, armazenando
		 * uma referencia a esse arquivo
		 * 
		 * AppConfig.class.getClassLoader().getResourceAsStream("config/app.config") : Quer dizer o seguinte
		 * "Veja no arquivo onde AppConfig esta armazenado/sendo carregado, se existe o arquivo "config/app.config"
		 * 
		 * getResourceAsStream() = ele procura no carregador da classe que o chamou se o arquivo no caminho informado existe, 
		 * se sim retorna ele em forma de INputStream se nn retorna null
		 * 
		 * '/' antes de 'config' quer dizer que esta procurando direto na raiz do classPath da aplicacao
		 */
		try(InputStream is = AppConfig.class.getResourceAsStream("/config/app.properties")){
			
			// se "config/app.config" nao existir!
			if(is == null) {
				throw new ErroConfigException("Arquivo 'app.properties' nao encontrado!");
			}
			
			/*
			 * props ele vai ler o arquivo obtido pelo InputStream is, e vai guardar como um Map
			 * o que cada referencia quer dizer!
			 */
			props.load(is);
		}
		catch(IOException e) {
			throw new ErroConfigException("config nao encontrada!");
		} 
	}
	
	
	/*
	 * Busca no arquivo "config/app.config" o que a palavra encontrada corresponde
	 */
	public static String get(String chave) {
		return props.getProperty(chave);
	}
}
