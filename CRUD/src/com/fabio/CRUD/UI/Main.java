package com.fabio.CRUD.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.fabio.CRUD.DTO.UsuarioDTO;
import com.fabio.CRUD.Fachada.Fachada;
import com.fabio.CRUD.dados.execeptions.ErroNaEntradaSaidaExcepiton;
import com.fabio.CRUD.negocio.exceptions.OperacaoDeUsuarioInvalidoException;
import com.fabio.CRUD.negocio.usuario.CodigoErroDTO;
import com.fabio.CRUD.negocio.usuario.TypeUser;
import com.fabio.CRUD.negocio.usuario.Usuario;


public class Main {
	
	public static void main(String[] args)  {
		
			Scanner scanf = new Scanner(System.in);
			
			
			System.out.println("-------------------------");
			System.out.println("|\tBEM-VINDO\t|");
			System.out.println("|     SISTEMA CRUD\t|");
			System.out.println("-------------------------\n");
			
			while(true) {
				String opcaoMenu, nome = null, email = null,senha = null ,dataCriacao, tipoDeUsuarioAuxiliar,emailBuscado;
				int menuErro;
				Usuario userBuscado = null;
				UsuarioDTO user = null;
				TypeUser tipo;
				Boolean status;
				
				System.out.println("O que deseja fazer?");
				System.out.println("1 - Cadastrar novo usuario");
				System.out.println("2 - Atualizar usuario");
				System.out.println("3 - Deletar usuario");
				System.out.println("4 - Buscar usuario");
				System.out.println("0 - Sair");
				
				opcaoMenu = Validacao.validarOpcaoMenu(scanf.next());
				scanf.nextLine(); // consumindo o '\n'
				
				switch(opcaoMenu) {
					case "1":
						
						
						System.out.println("Nome: ");
						nome = scanf.nextLine();
						System.out.println("Email: ");
						email = scanf.next().toLowerCase();
						System.out.println("Senha: ");
						senha = scanf.next();
						System.out.println("\nEscreva conforme informado a seguir, o tipo de usuario a ser criado:\n- Admin\n- Comum ");
						tipoDeUsuarioAuxiliar = scanf.next();
						tipo = Validacao.tipoDeUsuario(tipoDeUsuarioAuxiliar);
					
					//Alocando a data atual de cadastramento
					LocalDate agora = LocalDate.now();
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					dataCriacao = agora.format(formato);
					
					/*
					 * Status de criacao por padrao eh sempre "true"
					 */
					status = true;
					
					/*
					 * Abaixo ser[a realizado a criacao de um novo usuario
					 * Apartir de "user"
					 */
					try {
						
						/*
						 * Criando DTO de usuario!
						 */
						user = new UsuarioDTO(nome,email,senha,tipo,dataCriacao,status).validacao();
						
						//Tentativa de criar usuario
						Fachada.instance().criandoUser(user);
						
						System.out.println("\n[SUCESSO]:Usuario criado!\n");
						
					}
					catch(OperacaoDeUsuarioInvalidoException e) {
						if(e.getErro() == CodigoErroDTO.EMAIL_JA_CADASTRADO ) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
						if(e.getErro() == CodigoErroDTO.FORMATO_EMAIL_ERRADO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}	
						if(e.getErro() == CodigoErroDTO.TAMANHO_DE_NOME_INVALIDO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
							
						if(e.getErro() == CodigoErroDTO.TAMANHO_DE_SENHA_INVALIDO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
					}
					catch(ErroNaEntradaSaidaExcepiton e) {
						if(e.getErro() == CodigoErroDTO.ERRO_AO_LER_ARQUIVO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
						if(e.getErro() == CodigoErroDTO.ERRO_AO_SALVAR_ARQUIVO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
					
					}
					
					break;
				case "2":
					Usuario userAtualizado;
					String alteracao, emailChave;
					try {
						/*
						 * Busca o usuario a ser atualizado
						 */
						System.out.println("\nDigite o email do usuario: ");
						emailBuscado = scanf.next().toLowerCase(); 
						
						userBuscado = Fachada.instance().validandoEmail(emailBuscado).buscarUsuarioPorEmail(emailBuscado);
						
						/*
						 * serve para guarda a chave de acesso para o usuario escolhido!
						 * muito importante na hora de atualiza-lo
						 */
						emailChave = userBuscado.getEmail();
						
						/*
						 * Cria um novo usuario, afim de nao perder as informacoes antigas do usuario escolhido
						 */
						userAtualizado = new Usuario(userBuscado);
						
						/*
						 * Aqui eh selecionado o que o usuario deseja atualiza 
						 */
						System.out.println("\n\to que deseja atualizar?");
						System.out.println("\t1 - Email\n\t2 - Nome\n\t3 - Senha\n\t4 - Status\n\t5 - Sair");
						String opcao = Validacao.validarOpcaoMenu(scanf.next());
						scanf.nextLine();
						
							
						/*
						 * De acordo com a informacao que for escolhida para atualizar, sera validada a String,
						 * e se estiver dentro dos padroes, eh atualizada no usuario!
						 */
						if(opcao.equals("4")) {
							if(userBuscado.getStatus()) {
								userAtualizado.setStatus(false);
							}
							else {
								userAtualizado.setStatus(true);
							}
						}
						else {
							System.out.println("\nDigite a informacao atualizada: ");
							alteracao = scanf.nextLine();
							
							if(opcao.equals("1")) {
								Fachada.instance().validandoEmail(alteracao);
								userAtualizado.setEmail(alteracao);
							}
							if(opcao.equals("2")) {
								Fachada.instance().verificandoNome(alteracao);
								userAtualizado.setNome(alteracao);
							}
							if(opcao.equals("3")) {
								Fachada.instance().verificandoSenha(alteracao);
								userAtualizado.setSenha(alteracao);
							}
						}
						
						/*
						 * Checa se realmente houve alguma mudanca de userAtualizado para userBuscado!
						 * 
						 * Se forem iguais informa que nao ha motivo de mudanca
						 * 
						 * Se nao, atualiza o usuario!
						 */
						if(userBuscado.equals(userAtualizado)) {
							System.out.println("\nNenhuma mudanca foi realizada!\n");
						}
						else {
							Fachada.instance().atualizacaoDeUser(userAtualizado, emailChave);
							System.out.println("\n[SUCESSO]:Usuario Atualizado!\n");
							
						}
						
					}
					catch(OperacaoDeUsuarioInvalidoException e) {
						
						if(e.getErro() == CodigoErroDTO.EMAIL_JA_CADASTRADO ) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
						if(e.getErro() == CodigoErroDTO.FORMATO_EMAIL_ERRADO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}	
						if(e.getErro() == CodigoErroDTO.TAMANHO_DE_NOME_INVALIDO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
							
						if(e.getErro() == CodigoErroDTO.TAMANHO_DE_SENHA_INVALIDO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}	
						
					} catch (ErroNaEntradaSaidaExcepiton e) {
						if(e.getErro() == CodigoErroDTO.ERRO_AO_LER_ARQUIVO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
						if(e.getErro() == CodigoErroDTO.ERRO_AO_SALVAR_ARQUIVO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
						if(e.getErro() == CodigoErroDTO.USUARIO_NAO_ENCONTRADO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
					}
					break;
				case "3":
					System.out.println("Digite o email do usuario: ");
					emailBuscado = scanf.next().toLowerCase(); 
					
					try {
						
						/*
						 * Valida o email e se estiver tudo ok, por meio de encadeamento de metodos retorna a fachada.
						 * 
						 * Depois busca usuario por meio do email validado, de modo que se estiver tudo ok, retorna o
						 * Usuario buscado
						 */
						Fachada.instance().validandoEmail(emailBuscado).buscarUsuarioPorEmail(emailBuscado);
						
						Fachada.instance().deletarUser(emailBuscado);
						
						System.out.println("\n[SUCESSO]: Usuario deletado!\n");
						
					} 
					catch(OperacaoDeUsuarioInvalidoException e) {
						if(e.getErro() == CodigoErroDTO.FORMATO_EMAIL_ERRADO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}	
					}
					catch (ErroNaEntradaSaidaExcepiton e) {
						if(e.getErro() == CodigoErroDTO.ERRO_AO_LER_ARQUIVO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
						if(e.getErro() == CodigoErroDTO.ERRO_AO_SALVAR_ARQUIVO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
						if(e.getErro() == CodigoErroDTO.USUARIO_NAO_ENCONTRADO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
					} 
					
					break;
				case "4":
					
					System.out.println("Digite o email do usuario: ");
					emailBuscado = scanf.next().toLowerCase(); 
					
					try {
						
						/*
						 * Valida o email e se estiver tudo ok, por meio de encadeamento de metodos retorna a fachada.
						 * 
						 * Depois busca usuario por meio do email validado, de modo que se estiver tudo ok, retorna o
						 * Usuario buscado
						 */
						userBuscado = Fachada.instance().validandoEmail(emailBuscado).buscarUsuarioPorEmail(emailBuscado);
						
						//Imprime as informacoes de usuario na tela
						userBuscado.infoUser();
						
					} 
					catch(OperacaoDeUsuarioInvalidoException e) {
						if(e.getErro() == CodigoErroDTO.FORMATO_EMAIL_ERRADO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}	
					}
					catch (ErroNaEntradaSaidaExcepiton e) {
						if(e.getErro() == CodigoErroDTO.ERRO_AO_LER_ARQUIVO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
						if(e.getErro() == CodigoErroDTO.ERRO_AO_SALVAR_ARQUIVO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
						if(e.getErro() == CodigoErroDTO.USUARIO_NAO_ENCONTRADO) {
							System.out.println("\n\u001B[31m[ERRO]: " + e.getMessage() + "\n\u001B[0m");
						}
					} 
					
					
					break;
				case "5":
					break;
			}
		}
	}

}
