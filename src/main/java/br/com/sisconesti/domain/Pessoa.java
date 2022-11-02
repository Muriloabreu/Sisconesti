package br.com.sisconesti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Pessoa extends GenericDomain {
	
	@Column(length = 50, nullable = false)
	private String first_nome;
	
	@Column(length = 50, nullable = false)
	private String last_nome;
	
	@Column(length = 14, nullable = false)
	private String cpf;
	
	@Column(length = 50, nullable = false)
	private String email;
	
		
	public String getFirst_nome() {
		return first_nome;
	}

	public void setFirst_nome(String first_nome) {
		this.first_nome = first_nome;
	}

	public String getLast_nome() {
		return last_nome;
	}

	public void setLast_nome(String last_nome) {
		this.last_nome = last_nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
