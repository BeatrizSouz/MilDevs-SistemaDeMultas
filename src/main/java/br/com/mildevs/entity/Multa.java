package br.com.mildevs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Multa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "código_multa")
	private Long codMulta;
	
	@Column(nullable = false )
	private double valorMulta;

	@Column(nullable = false )
	private int pontuacao;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="placa_fk", referencedColumnName = "placa") 
	private Veiculo veiculo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="numero_CNH_fk", referencedColumnName = "numero_CNH") 
	private Condutor condutor;
	
	
	public Long getCodMulta() {
		return codMulta;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	public double getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(double valorMulta) {
		this.valorMulta = valorMulta;
	}
	public Veiculo getVeiculomultado() {
		return veiculo;
	}
	
	
	public void setVeiculomultado(Veiculo veiculomultado) {
		this.veiculo = veiculomultado;
	}

	public Condutor getCondutor() {
		return condutor;
	}
	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}
	@Override
	public String toString() {
		return "\n-----------------------------------------------------------------------------------\n"
				+"Multa do Condutor: "+condutor.getNomeCondutor() + "\n\n" + 
				"Placa do Veiculo: " + veiculo.getPlaca() + "\t|" +
				"\tCódigo Da Multa: " + codMulta + "\t|" +
				"\nValor Da Multa: " + valorMulta + "\t| "+
				"\tPontuação: " + pontuacao + "\t\t|" +
		
				"\n-----------------------------------------------------------------------------------\n";
	}
	
	
	
	
	

	
}
