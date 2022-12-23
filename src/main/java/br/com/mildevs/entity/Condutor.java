package br.com.mildevs.entity;


import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Condutor {
	
	@Id 
	@Column(name = "numero_CNH", nullable = false)
	private int numCnh;
	
	
	@Column(name = "data_emissão", nullable = false)
	private LocalDate dataEmissao;
	
	@Column(nullable = false)
	private String orgaoEmissor;
	
	@Column(nullable = false)
	private int pontuacao;
	
	@Column(nullable = false )
	private String nomeCondutor;
	

   @OneToMany(mappedBy = "condutor", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY, orphanRemoval = true)
   private List<Multa>multas;
	
    @OneToOne(mappedBy = "condutor", cascade = { CascadeType.REFRESH, CascadeType.PERSIST }, fetch = FetchType.LAZY, optional = true, orphanRemoval = true)
    private Veiculo veiculo;
	
	
	
	public String getNomeCondutor() {
		return nomeCondutor;
	}
	
	public void setNomeCondutor(String nomeCondutor) {
		this.nomeCondutor = nomeCondutor;
	}
	
	public int getNumCnh() {
		return numCnh;
	}
	
	public void setNumCnh(int numCnh) {
		this.numCnh = numCnh;
	}
	
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}
	
	public void setDataEmissao(LocalDate dataEmissao) {
		
			this.dataEmissao = dataEmissao;
		
	}
	
	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}
	
	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	
	public List<Multa> getMultas() {
		return multas;
	}

	public void setMultas(List<Multa> multas) {
		this.multas = multas;
	}

	@Override
	public String toString() {
		return "\n-----------------------------------------------------------------------------------\n"
				+"Condutor: "+ nomeCondutor + "\n\n" +  
				"Orgão Emissor: " + orgaoEmissor + "\t\t\t|" +
				 "\tNúmero da CNH: " + numCnh + "\t\t|" +
				 "\nPontuacão da carteira: " + pontuacao + "\t\t| "+
				 "\tData de Emissão: " + dataEmissao+ "\t|" +
				
				"\n-----------------------------------------------------------------------------------\n";
	}
	
	
	

	
}
