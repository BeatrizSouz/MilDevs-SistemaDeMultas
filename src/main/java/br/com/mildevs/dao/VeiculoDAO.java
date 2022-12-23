package br.com.mildevs.dao;

import java.util.List;

import br.com.mildevs.entity.Condutor;
//import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class VeiculoDAO {
	
	private EntityManager manager;

	public VeiculoDAO() {
		
		this.manager = Persistence.createEntityManagerFactory("sistemamultas").createEntityManager();
	}
	
	
	//Criar veiculo
	public boolean criarVeiculo(Veiculo veiculoNovo, int numCNH) {
		
	Condutor condutor = manager.find(Condutor.class, numCNH);	
	
	
	if (condutor == null) {
		System.err.println("Não existe condutor com o CNH: "+ numCNH);
		return false;
	}
	
	condutor.setVeiculo(veiculoNovo);
	veiculoNovo.setCondutor(condutor);
	
	this.manager.getTransaction().begin();
	this.manager.persist(veiculoNovo);
	this.manager.getTransaction().commit();
		
	return true;
	
			
		
	}
	
	//consultar 
	public Veiculo consultaVeiculo (String placa) {
		
		return manager.find(Veiculo.class, placa);
	}
	
	
	//listar 
	public List<Veiculo> listarVeiculos () {
		Query query = manager.createQuery("SELECT v FROM Veiculo as v");
		
	
		return query.getResultList();
	}

	public boolean removeVeiculo (String placa) {
		
		Veiculo veiculo = manager.find(Veiculo.class, placa);
		
		
		if (veiculo == null) {
			System.err.println("Não existe veiculo com essa placa!");
			return false;
		}
		
		Condutor  condutor = veiculo.getCondutor();
		veiculo.setCondutor(null);
		condutor.setVeiculo(null);
		
		this.manager.getTransaction().begin();
		this.manager.remove(veiculo);
		this.manager.getTransaction().commit();
		
		return true;
		
		
	}

	
	public boolean vendaVeiculo(String placa, int novoDonoCNH) {
		Veiculo veiculoAVenda = new Veiculo();
		Condutor  novoDono = new Condutor();
        veiculoAVenda =  manager.find(Veiculo.class, placa);
		novoDono = manager.find(Condutor.class, novoDonoCNH);	
		Condutor antigoDono = veiculoAVenda.getCondutor();
		
		
		if ( antigoDono.getNumCnh() == novoDonoCNH) {
			System.err.println("O veiculo com a placa: "+ placa + " já te pertence!");
			return false;
		}
		
		if (novoDono == null) {
			System.err.println("Não existe condutor com o CNH: "+ novoDonoCNH);
			return false;
		}
		
	
		novoDono.setVeiculo(veiculoAVenda);
		veiculoAVenda.setCondutor(novoDono);
		
		manager.getTransaction().begin();
		manager.merge(veiculoAVenda);;
		manager.getTransaction().commit();
			
		return true;
		
		
	}
}
