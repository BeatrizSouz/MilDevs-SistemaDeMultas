package br.com.mildevs.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Veiculo {

	@Id
	@Column( nullable = false)
	private String placa;
	
	@Column()
	private int ano;
	
	@Column()
	private String modelo;
	
	@Column( )
	private String marca;
	
	@OneToOne( cascade = { CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CNH_Condutor_PK", referencedColumnName = "numero_CNH")
	private Condutor condutor;
	
	@OneToMany(mappedBy = "veiculo",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Multa>multas;
	
	

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}



	public List<Multa> getMultas() {
		return multas;
	}

	public void setMultas(List<Multa> multa) {
		this.multas = multa;
	}
	
	@Override
	public String toString() {
		return "\n-----------------------------------------------------------------------------------\n"+
				 "Placa: " + placa +", Ano:" + ano +", Modelo: " + modelo + ", Marca: " + marca +"\n"+
				  condutor +
				  multas +"\n";
	}
	
	public String imprimeMultaVeiculo() {
		return "\n-----------------------------------------------------------------------------------\n"+
				  "Placa: " + placa  + "\t\t|" +
				 "\tAno:" + ano + "\t\t|" +
				 "\nModelo: " + modelo + "\t\t|" +
				 "\tMarca: " + marca + "\t\t|\n"+
				 "\n-----------------------------------------------------------------------------------\n";
	}


	
	
}
