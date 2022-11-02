package br.com.sisconesti.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisconesti.domain.Equipamento;
import br.com.sisconesti.domain.Fabricante;
import br.com.sisconesti.domain.Funcionario;

public class EquipamentoDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(5L);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(3L);
		
		
		Equipamento equipamento = new Equipamento();
		equipamento.setTipo("Celular");
		equipamento.setModelo("xxxxxxx");
		equipamento.setN_serie("x1x1x1x1");
		equipamento.setN_tag("xaxaxaxaxa");
		equipamento.setStatus('U');
		equipamento.setObservacao("asadasadasadasadasadasadasadasadasadasadasaas");
		equipamento.setFabricante(fabricante);
		equipamento.setFuncionario(funcionario);
		
		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
		equipamentoDAO.merge(equipamento);
		
		
		
	}
	
	
	

}
