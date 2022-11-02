package br.com.sisconesti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@SuppressWarnings("unused")
@Entity
public class Equipamento extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String tipo;
	
	@Column(length = 50, nullable = false)
	private String modelo;
		
	@OneToOne
	@JoinColumn(nullable = false)
	private  Fabricante fabricante;
	
	@Column(length = 50, nullable = false)
	private String n_serie;
	
	@Column(length = 50, nullable = false)
	private String n_tag;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
	
	@Column(length = 500, nullable = true)
	private String observacao;
	
	@Column(nullable = false)
	private Character status;

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getN_serie() {
		return n_serie;
	}

	public void setN_serie(String n_serie) {
		this.n_serie = n_serie;
	}

	public String getN_tag() {
		return n_tag;
	}

	public void setN_tag(String n_tag) {
		this.n_tag = n_tag;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
	
	@Transient // informa que esse dados não tem no banco de dados
	public String getStatusFormatado() {
		String statusFormatado = null;
		
		if (status == 'B') {
			
			statusFormatado = "Backup";
		}else if(status == 'U') {
			
			statusFormatado = "Em Uso";
		}
		
		return statusFormatado;
	}
	
	
}
