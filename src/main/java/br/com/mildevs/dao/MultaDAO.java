package br.com.mildevs.dao;

import java.util.List;

import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class MultaDAO {
	
	private EntityManager manager;

	public MultaDAO() {
		
		this.manager = Persistence.createEntityManagerFactory("sistemamultas").createEntityManager();
	}
	
	
	//CriarMulta
	public boolean aplicarMulta(Multa multaNova, String placa, int numCNH ) {
		
		Veiculo veiculo = manager.find(Veiculo.class,placa);
		Condutor condutor = manager.find(Condutor.class, numCNH);
		
		
		if(veiculo == null ) {
			System.err.println("NÃO EXISTE VEICULO PARA MULTAR");
			return false;
		}
		
		if(!(veiculo.getCondutor() == condutor)) {
			System.err.println("O veiculo indicado não pertence ao condutor com o CNH: "+numCNH);
			return false;
		}
		
		condutor.setPontuacao(condutor.getPontuacao() + multaNova.getPontuacao());
		multaNova.setVeiculomultado(veiculo);
		multaNova.setCondutor(condutor);
		
		if (condutor.getPontuacao() <= 40 ) {
			
			this.manager.getTransaction().begin();
			this.manager.persist(multaNova);
			this.manager.getTransaction().commit();
			
		}else if (condutor.getPontuacao() > 40) {
			
			
			System.err.println("A pontuação máxima já foi atingida!\nEste condutor perdeu a carteira!");
			return false;
		}
		
		
		return true;
		
	}
	
	
	//Consultar multa por cod
	public List<Multa> consultaMultaPorVeiculo (String placa) {
		Veiculo veiculo = manager.find(Veiculo.class, placa);
		
		if (veiculo == null) {
			System.err.println("O veiculo não existe");
			
			return null;
		}
		
		
		List<Multa> multa = veiculo.getMultas();
		
		if (multa.isEmpty()) {
			System.err.println("A multa não existe");
			
			return null;
		}

		return multa;
	}
	
	
	//Lista de multas
	public List<Multa> listarMultas () {
	
		Query query = manager.createQuery("SELECT m FROM Multa as m ");
		
		
		
		return query.getResultList();
	}
	
	public boolean removeMulta (Long codMulta) {
		
		Multa multa  = manager.find(Multa.class, codMulta);
		
		if (multa == null) {
			System.err.println("Não existe uma multa com esse código");
			return false;
		}

		//System.out.println("A multa do condutor "+multa.getCondutor().getNomeCondutor()+" foi removida! ");
		multa.setVeiculomultado(null);
		multa.setCondutor(null);
		
		this.manager.getTransaction().begin();
		this.manager.remove(multa);
		this.manager.getTransaction().commit();
		
		
		return true;
		
		
	}

}
