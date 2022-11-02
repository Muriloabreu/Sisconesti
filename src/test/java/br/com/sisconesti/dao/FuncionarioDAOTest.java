package br.com.sisconesti.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import br.com.sisconesti.domain.Funcionario;

public class FuncionarioDAOTest {
	
	
	@Test
	//@Ignore
	public void listar() {
		
		FuncionarioDAO func01 = new FuncionarioDAO();
		List<Funcionario> resultado = func01.listar();
		
		

		for (Funcionario funcionario : resultado) {
			System.out.println("Código da Cidade: " + funcionario.getId());
			System.out.println("Matricula: " + funcionario.getMatricula());
			System.out.println("Nome: " + funcionario.getPessoa().getFirst_nome());
			System.out.println("Sobrenome : " + funcionario.getPessoa().getLast_nome());
			System.out.println("Empresa: " + funcionario.getEmpresa().getNome());
			System.out.println("Setor: " + funcionario.getSetor().getNome());
			System.out.println("Cidade: " + funcionario.getEmpresa().getCidade().getNome());
			
			System.out.println();
		}
	}

}
