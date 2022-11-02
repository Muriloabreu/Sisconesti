package br.com.sisconesti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("unused")
@Entity
public class Fabricante extends GenericDomain{
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
