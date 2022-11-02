package br.com.sisconesti.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.sisconesti.domain.Pessoa;

@Entity
public class Usuario extends GenericDomain {

	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	public Character getTipo() {
		return tipo;
	}
	
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}
	
	@Transient // informa que esse dados não tem no banco de dados
	public String getTipoFormatado() {
		String tipoFormatado = null;
		
		if (tipo == 'A') {
			
			tipoFormatado = "Administrador";
		}else if (tipo == 'C') {
			
			tipoFormatado = "Colaborador";
		}else if (tipo == 'G') {
			
			tipoFormatado = "Gerente";
		}
		
		return tipoFormatado;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Transient // informa que esse dados não tem no banco de dados
	public String getAtivoFormatado() {
		
		String ativoFormatado = "Desativado";
		
		if(ativo) {
			ativoFormatado = "Ativo";
		}
		
		return ativoFormatado;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
