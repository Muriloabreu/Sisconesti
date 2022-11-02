package br.com.sisconesti.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisconesti.domain.Cidade;
import br.com.sisconesti.domain.Empresa;


public class EmpresaDAOTest {
	
	
	@Test
	//@Ignore
	public void salvar() {
		
		Long codigoCidade = 1L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();

		Cidade cidade = cidadeDAO.buscar(codigoCidade);

		Empresa empresa = new Empresa();
		empresa.setNome("Bunge Moinho Ipojuca");
		empresa.setCidade(cidade);
		
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresaDAO.salvar(empresa);
	
	}

}
