package br.com.sisconesti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SetorEmpresa extends GenericDomain {

	@Column(length = 32, nullable = false)
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
