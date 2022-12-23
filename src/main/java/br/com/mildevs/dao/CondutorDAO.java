package br.com.mildevs.dao;

import java.util.List;



import br.com.mildevs.entity.Condutor;
import br.com.mildevs.entity.Multa;
import br.com.mildevs.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class CondutorDAO {
	
	private EntityManager manager;
	

	public CondutorDAO() {
		
		this.manager = Persistence.createEntityManagerFactory("sistemamultas").createEntityManager();
	}
	
	
	//CriarCondutor 
	public boolean criarCondutor(Condutor condutor) {
		
		
		this.manager.getTransaction().begin();
		this.manager.persist(condutor);
		this.manager.getTransaction().commit();
		
		return true;
	}
	
	//Consultar condutor por CNH
	public Condutor consultaCondutor (int numCnh) {
		if (manager.find(Condutor.class, numCnh)== null) {
			System.err.println("o condutor não existe");
			return null;
		}
		return manager.find(Condutor.class, numCnh);
		
	}
	
	
	//Lista de condutores
	public List<Condutor> listarCondutores () {
		Query query = manager.createQuery("SELECT c FROM Condutor as c ORDER BY numero_cnh ASC ");
		

		return query.getResultList();
	}
	
	
	public boolean removeCondutor (int numCnh) {
		
		Condutor condutor = manager.find(Condutor.class, numCnh);
		
		if (condutor == null) {
			return false;
		}
		
		this.manager.getTransaction().begin();
		this.manager.remove(condutor);
		this.manager.getTransaction().commit();
		
		return true;
		
		
	}
	

}
