package br.com.sisconesti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Funcionario  extends GenericDomain {
	
	@Column(length = 20, nullable = false)
	private String matricula;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;	
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;	

	@OneToOne
	@JoinColumn(nullable = false)
	private SetorEmpresa setor;		
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public SetorEmpresa getSetor() {
		return setor;
	}
	
	public void setSetor(SetorEmpresa setor) {
		this.setor = setor;
	}

}
