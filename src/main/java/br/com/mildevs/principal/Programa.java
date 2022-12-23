package br.com.mildevs.principal;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Programa {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Menu menu = new Menu();
		
		boolean verifica = true;

	
	try {
		
		while(verifica ){
		
		
				menu.menuPrincipal();
				
				System.out.print("Informe o processo:");
				int opcao = input.nextInt();
				
				
				switch(opcao) {
				
					case 1: 
						menu.menuProcessoCondutor();
						
						break;
						
					case 2:
						
						menu.menuProcessoVeiculo();
						
						break;
					case 3:
						
						menu.menuProcessoMulta();
						
						
						break;
					
					case 4:
						System.out.println("Programa encerrado");
						verifica = false;
						
						break;
						
					default: 
						System.err.println("Opção errada! Por favor, digite uma opção válida.");
						
				}
			
			
			
		}
		
		} catch (InputMismatchException e) {
			System.err.println("Digite apenas números");

			
		}
		
		
		//Criar condutor-com veiculo
		
	 /*   Condutor motorista = new Condutor();
		
		motorista.setNomeCondutor("Mário Júnior Silva");
		motorista.setNumCnh(123);
		motorista.setDataEmissao(LocalDate.of(2003, 02, 14));
		motorista.setOrgaoEmissor("BA");
		motorista.setPontuacao(50);
		
		//preciso perguntar se tem um veiculo ou não
		//Criar veiculo
		//perguntar sobre o veiculo :
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("XULM40");
		veiculo.setAno(LocalDate.ofYearDay(2003, 15));
		veiculo.setModelo("modelox");
		veiculo.setMarca("FIAT");
		
	
		//E só preciso settar o veiculo e o condutor
		motorista.setVeiculo(veiculo);
		veiculo.setCondutor(motorista);
		condutorDAO.criarCondutor(motorista);
		
		
		
		//segundo condutor-agr sem veiculo
		Condutor motorista1 = new Condutor();
		
		motorista1.setNomeCondutor("Marta de Souza ");
		motorista1.setNumCnh(444);
		motorista1.setDataEmissao(LocalDate.of(2003, 02, 14));
		motorista1.setOrgaoEmissor("BA");
		motorista1.setPontuacao(50);
		
		
		condutorDAO.criarCondutor(motorista1);
		
		//veiculoDAO.criarVeiculo(motorista,veiculo, 444);
		
		//consulte o que esta na tabela 
		System.out.println(condutorDAO.consultaCondutor(123));
		System.out.println(veiculoDAO.consultaVeiculo("XULM40"));
		
		
		
		//criando um veiculo para o segundo condutor 
		Veiculo veiculo1 = new Veiculo();
		veiculo1.setPlaca("cvu556");
		veiculo1.setAno(LocalDate.ofYearDay(2015, 15));
		veiculo1.setModelo("caminhão");
		veiculo1.setMarca("VXV");
		
		veiculoDAO.criarVeiculo(veiculo1, 444);
		
		///System.out.println(veiculoDAO.consultaVeiculo("cvu556"));
		
		
		//lsitar tosos os veiculos :
		
		//System.out.println(veiculoDAO.listarVeiculos());
		
		
		
		//veiculoDAO.removeCondutor("cvu556"); // remove um
		//System.out.println(veiculoDAO.listarVeiculos());

		Condutor motorista2 = new Condutor();
		
		motorista2.setNomeCondutor("Evandro Pereira ");
		motorista2.setNumCnh(555);
		motorista2.setDataEmissao(LocalDate.of(1998, 02, 14));
		motorista2.setOrgaoEmissor("BA");
		motorista2.setPontuacao(50);
		
	
		condutorDAO.criarCondutor(motorista2);
		
		
		//System.out.println(veiculoDAO.vendaVeiculo("cvu556", 555));
		
//		veiculoDAO.criarVeiculo(veiculo, 123 );
		
		//System.out.println(condutorDAO.listarCondutores());
		
		//condutorDAO.removeCondutor(123);
		
	/*	System.out.println(condutorDAO.listarCondutores());
		System.out.println(veiculoDAO.listarVeiculos()); 
		condutorDAO.removeCondutor(123);
		veiculoDAO.removeVeiculo("cvu556");
		System.out.println(veiculoDAO.listarVeiculos()); 
		
		System.out.println(condutorDAO.listarCondutores());*/
		
	
		

		//ESTE PROCESSO DEVE DAR CERTO! O VEICULO EXISTE!*/
	/*	Multa processoMulta = new Multa();
		processoMulta.setCodMulta(12);
		processoMulta.setPontuacao(55);
		processoMulta.setValorMulta(200);
		
	
		
		
		//multaDAO.criarMulta(processoMulta);
		multaDAO.aplicarMulta(processoMulta, "cvu556", 444);
		
		
		///ESTE PROCESSO Ñ DEVE DAR CERTO! O VEICULO Ñ EXISTE!
		Multa processoMulta2 = new Multa();
		processoMulta2.setCodMulta(13);
		processoMulta2.setPontuacao(55);
		processoMulta2.setValorMulta(200);

		
		multaDAO.aplicarMulta(processoMulta2,"djddh",123);
		
		Multa processoMulta3 = new Multa();
		processoMulta3.setCodMulta(18);
		processoMulta3.setPontuacao(55);
		processoMulta3.setValorMulta(200);

		
		multaDAO.aplicarMulta(processoMulta3,"XULM40",123);*/
		
		
	//	System.out.println(	multaDAO.consultaMulta(12));
		//System.out.println(multaDAO.consultaMulta(13));
		//System.out.println(multaDAO.listarMultas());
		
		//System.out.println(multaDAO.removeMulta(12));
		
	//	System.out.println(multaDAO.listarMultas());
		
		//System.out.println(condutorDAO.listarCondutores());
		/*for(Veiculo s : veiculoDAO.listarVeiculos()){
			   System.out.print(s);
			}
		
		*/
		
		input.close();
	

		
		
		
		
		
		
		
		
	}


	
	

	
	
}
