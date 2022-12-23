package br.com.mildevs.principal;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import br.com.mildevs.dao.CondutorDAO;
import br.com.mildevs.dao.MultaDAO;
import br.com.mildevs.dao.VeiculoDAO;
import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;


public class Menu {
	
	private  int opcao;
	private String continuar;
	public static final String ANSI_RESET = "\033[0m";
	public static final String corVerde = "\033[0;32m";
	public static final String corAmarelo = "\033[0;33m";
	public static final String corVermelho = "\033[0;31m";
	
	public void menuPrincipal() {
		limparTela();
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("\t Sistema de Multas \t");
		System.out.println("-----------------------------------------------------------------------------------------");

		System.out.println("1 - Processo para Condutor");
		System.out.println("2 - Processo para Veiculo");
		System.out.println("3 - Processo para Multa");
		System.out.println("4 - Finalizar");
		
	}

	public static void menuEntidade(String entidade, String p) {
		limparTela();
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("\t Processo para " + entidade + " \t");
		System.out.println("-----------------------------------------------------------------------------------");

		System.out.println("1 - Cadastrar " + entidade);
		System.out.println("2 - Consultar " + entidade);
		System.out.println("3 - Listar todas as(os) " + entidade+p);
		System.out.println("4 - Excluir " + entidade);
		System.out.println("5 - Voltar para menu principal");

		
	}
	
	public void menuProcessoCondutor() {
		
		int numCNH;
		boolean verifica = true;
		
		try {
			
			
		while(verifica){	
			
			Scanner input = new Scanner(System.in);
			Condutor motorista = new Condutor();
			CondutorDAO condutorDao = new CondutorDAO();
			
			menuEntidade("Condutor", "es");
			
			System.out.print("Informe o processo:");
			opcao = input.nextInt();
			
			switch(opcao) {
			case 1: 		
				
			System.out.println("\nOpção 1 - Cadastrar condutor");	
			inserirCondutor(motorista, condutorDao, input);
			
				break;
			case 2:
				System.out.println("\nOpção 2 - Consultar condutor pelo número do CNH");
				System.out.print("Infome o número do CNH:  ");
				 numCNH = input.nextInt();
				System.out.println(corAmarelo + condutorDao.consultaCondutor(numCNH) + ANSI_RESET);
				
			
				break;
			case 3:
				
			System.out.println("\nOpção 3 - Listagem dos condutores presentes no banco de dados: ");	
				if (condutorDao.listarCondutores().isEmpty()) {
					
					System.err.println("Não existe condutores cadasdastrados no banco!");
					
				}else {
					
					for(Condutor lista : condutorDao.listarCondutores()){
						   System.out.print(corAmarelo + lista + ANSI_RESET);
						}
				
				
				}
				
				break;
				
			case 4:
				
				System.out.println("\nOpção 4 -  Excluir condutor baseado no número do CNH ");
				System.out.print("Infome o número do CNH:  ");	
				numCNH = input.nextInt();
				input.nextLine();
				
				System.out.println(corVermelho+"Atenção! Essa ação excluirá o condutor, seu veiculo e multa do banco de dados.");
				System.out.print("Deseja continuar? S/N " + ANSI_RESET);
				
				continuar = input.nextLine();
				
				if(continuar.equalsIgnoreCase("S")) {
					System.out.println(corVerde + "O condutor foi removido com sucesso!"+  ANSI_RESET);
					condutorDao.removeCondutor(numCNH);
					
				}else {
					System.out.println(corVermelho+"Operação cancelada!"+ ANSI_RESET);
				}
			
			
				break;
			case 5:		
				verifica = false;
				
				
				break;
			default:
				System.out.println(corVermelho+"Opção errada! Por favor, digite uma opção válida."+ ANSI_RESET);
				break;
			
			}
			
			
			
		}
			
		} catch (InputMismatchException  e) {
			System.err.println("O valor foi digitado errado, tente mais uma vez!");
			menuProcessoCondutor();
			
		} catch (DateTimeParseException e) {
			System.err.println("A data foi digitada errada!Digite a data de acordo com o exemplo:\n06/02/2003");
			menuProcessoCondutor();
		}
		
		
		

		
	}
	
	private void inserirCondutor(Condutor motorista, CondutorDAO condutorDao, Scanner input) {
		String nome;
		int numCNH;
		String dataEmissao;
		String orgaoEmissor;
		int pontuacao;
		
		System.out.print("Digite o nome do condutor:");
		input.nextLine();
		nome = input.nextLine();
		
		System.out.print("Digite o número do CNH:");
		numCNH = input.nextInt();
		
		System.out.print("Digite a data de emissão do documento:");
		input.nextLine();
		dataEmissao = input.nextLine();
		
		System.out.print("Digite o orgão emissor da carteira:");
		orgaoEmissor = input.nextLine();
		
		System.out.print("Digite a pontuação da carteira:");
		pontuacao = input.nextInt();
		
		
		motorista.setNomeCondutor(nome);
		motorista.setNumCnh(numCNH);
		motorista.setDataEmissao(converteData(dataEmissao));
		motorista.setOrgaoEmissor(orgaoEmissor);
		motorista.setPontuacao(pontuacao);
		
		
		condutorDao.criarCondutor(motorista);
		System.out.println(corVerde + "Condutor cadastrado com sucesso!" + ANSI_RESET);

		
		
	}


	private LocalDate converteData(String dataRecebidaMotorista) {
	 
		DateTimeFormatter formatt  = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataConvertida = LocalDate.parse(dataRecebidaMotorista, formatt);
		return dataConvertida;
		
	}

	public void menuProcessoMulta() {
	
		String placa;
		boolean verifica = true;
				
		try {
			while(verifica ) {
			Scanner input = new Scanner(System.in);
			Multa multa = new Multa();
			MultaDAO multaDao = new MultaDAO();
				
				
			menuEntidade("Multa","s");
			
			System.out.print("Informe o processo:");
			opcao = input.nextInt();
			input.nextLine();
			
			switch(opcao) {
			case 1: 	
				
				System.out.println("\nOpção 1 - Cadastrar multa ");	
				inserirMulta(multa,multaDao,input);
			
			
				break;
			case 2:
			
				System.out.println("\nOpção 2 - Consultar multa pela placa do veiculo: ");
				System.out.print("Insira a placa do veiculo:");
				placa = input.nextLine();
				for(Multa Multas : multaDao.consultaMultaPorVeiculo(placa)){
					   System.out.print(corAmarelo +Multas+  ANSI_RESET);
				}
				
				break;
			case 3:
				
				System.out.println("\nOpção 3 - Listagem de todas as multas presentes no banco de dados ");	
				
				
				if (multaDao.listarMultas().isEmpty()) {
					System.err.println("Não existe multas no banco!");
					
					
				}else {
					
					for(Multa lista : multaDao.listarMultas()){
						   System.out.print(corAmarelo +lista+  ANSI_RESET);
						}
				
				}
				
				
				
				break;
			case 4:
				
				System.out.println("\nOpção 4 - Excluir multa com base no código da multa");
				System.out.print("Informe o código da multa: "); 	
				Long codMulta = (long) input.nextInt();
				
				System.out.println(corVermelho +"Atenção! Essa ação excluirá apenas a multa, condutor e veiculo continuam no banco de dados.");
				System.out.print("Deseja continuar? S/N " + ANSI_RESET);
				input.nextLine();
				
				continuar = input.nextLine();
				
				if(continuar.equalsIgnoreCase("S")) {
					multaDao.removeMulta(codMulta);
					System.out.println(corVerde + "Multa removida com sucesso!"+  ANSI_RESET);
					
				}else {
					System.out.println(corVermelho +"Operação cancelada." +  ANSI_RESET);
				}
			
				break;
			case 5:		
				verifica = false;
				
				break;
			default:
				System.err.println("Opção errada! Por favor, digite uma opção válida.");
				break;
			
			}
			
		}
			
		} catch (InputMismatchException e) {
				System.err.println("O valor foi digitado errado, tente mais uma vez!");
				menuProcessoMulta();
				
		} 
			
	
		
	}
	
	private void inserirMulta(Multa multa, MultaDAO multaDao,Scanner input) {
		String placa;
		int numCNH;
		int pontuacao;
		double valorMulta;
		
		
		System.out.print("Digite o número do CNH que deseja multar:");
		numCNH = input.nextInt();
		
		System.out.print("Digite a placa do veiculo:");
		input.nextLine();
		placa = input.nextLine();
		
		System.out.print("Digite o valor da multa:");
		valorMulta = input.nextDouble();
		
		System.out.print("Digite o valor da pontuação na carteira:");
		pontuacao = input.nextInt();
		
		multa.setPontuacao(pontuacao);
		multa.setValorMulta(valorMulta);
		

		multaDao.aplicarMulta(multa, placa, numCNH);
		System.out.println(corVerde +"O VEICULO DO(A) MOTORISTA " + multa.getCondutor().getNomeCondutor() +", CUJO O NÚMERO DA PLACA É "+ placa +",  FOI MULTADO !"+ANSI_RESET);

		

		
		
	}

	public void menuProcessoVeiculo() {
		String placa;
		boolean verifica = true;
		
		try {
			
			
		while(verifica){
			Scanner input = new Scanner(System.in);
			VeiculoDAO veiculoDao = new VeiculoDAO();
			Veiculo veiculo = new Veiculo();
			
			menuEntidade("veiculo","s");
			
			System.out.print("Informe o processo:");
			opcao = input.nextInt();
			input.nextLine();
			
			switch(opcao) {
			case 1: 		
				
			System.out.println("\nOpção 1 - Cadastrar novo veiculo");	
			inserirVeiculo(veiculo, veiculoDao, input);
			
				break;
			case 2:
				
				System.out.println("\nOpção 2 - Consultar veiculo pela placa");
				System.out.print("Infome a placa do veiculo:  ");
				 placa = input.nextLine();
				System.out.println( corAmarelo +veiculoDao.consultaVeiculo(placa)+ ANSI_RESET);
				
			
				break;
			case 3:
				
			System.out.println("\nOpção 3 - Listagem dos veiculos presentes no banco de dados: ");	
				if (veiculoDao.listarVeiculos().isEmpty()) {
					
					System.out.println(corVermelho+"Não existe veiculos cadasdastrados no banco!"+ ANSI_RESET);
					
				}else {
					
					for(Veiculo lista : veiculoDao.listarVeiculos()){
						   System.out.print(corAmarelo +lista+ ANSI_RESET);
						}
				
				
				}
				
				break;
				

			case 4:
				
				System.out.println("\nOpção 4 -  Excluir veiculo baseado na placa ");
				System.out.print("Infome a placa do veiculo:  ");
				placa = input.nextLine();
				
				System.out.println(corVermelho+"Atenção! Essa ação também excluirá o as multas pertencentes a esse veiculo, mas não o condutor.");
				System.out.print("Deseja continuar? S/N " + ANSI_RESET);
				continuar = input.nextLine();
				
				if(continuar.equalsIgnoreCase("S")) {
					System.out.println(corVerde + "O veiculo foi removido com sucesso!"+  ANSI_RESET);
					veiculoDao.removeVeiculo(placa);
					
				}else {
					System.out.println(corVermelho+"Operação cancelada!"+ ANSI_RESET);
					
				}
				
			
				break;
			
			case 5:		
				verifica = false;
				
				break;
			default:
				System.err.println("Opção errada! Por favor, digite uma opção válida.");
				break;
			
			}
			
			
			
		}
			
		} catch (InputMismatchException e) {
			System.err.println("O valor foi digitado errado, tente mais uma vez!");
			menuProcessoVeiculo();
		
		}/* catch (NullPointerException e) {
			System.err.println("O correu um erro durante o processo, tente novamente e verifique se o condutor existe.");
			menuProcessoVeiculo();				
		}*/
		


		
		

		
	}
	
	private void inserirVeiculo(Veiculo veiculo, VeiculoDAO veiculoDao, Scanner input) {
		
		
		String placa;
		String modelo;
		String marca;
		int ano;
		int condutorCNH;
		
		System.out.print("Informe a placa do veiculo: ");
		placa = input.nextLine();
		
		
		System.out.print("Informe o ano do veiculo: ");
		ano = input.nextInt();
		input.nextLine();
		
		System.out.print("Informe o modelo do veiculo: ");
		modelo = input.nextLine();
		
		System.out.print("Informe a marca do veiculo: ");
		marca = input.nextLine();
		
		System.out.print("Informe o número do CNH do condutor: ");
		condutorCNH = input.nextInt();
		
		veiculo.setPlaca(placa);
		veiculo.setAno(ano);
		veiculo.setModelo(modelo);
		veiculo.setMarca(marca);
		
		
		
		 veiculoDao.criarVeiculo(veiculo, condutorCNH);
		 System.out.println(corVerde +"O VEICULO DO(A) MOTORISTA " + veiculo.getCondutor().getNomeCondutor() +", FOI CADASTRADO !"+ANSI_RESET);

		
	}

	

	public static void limparTela() {
		
		if (System.getProperty("os.name").contains("Windows"  ) || System.getProperty("os.name").contains("Mac.Os"  ))
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		else
			try {
				Runtime.getRuntime().exec("clear");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
		
	}
	
	
}
