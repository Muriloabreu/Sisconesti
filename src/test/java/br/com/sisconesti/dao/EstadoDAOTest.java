package br.com.sisconesti.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sisconesti.dao.EstadoDAO;
import br.com.sisconesti.domain.Estado;

@SuppressWarnings("unused")
public class EstadoDAOTest {
	
	
	@Test
	@Ignore
	public void salvar() {
		
		Estado estado = new Estado();
		estado.setNome("Pernambuco");
		estado.setSigla("PE");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {
		
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Estado estado : resultado) {
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		
		Long codigo = 1L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado:");
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 27L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("Registro removido:");
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void editar() {
		
		Long codigo = 1L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
			
		} else {
			
			System.out.println("Registro editado - Antes:");
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());

			estado.setNome("Pernambuco");
			estado.setSigla("PE");
			estadoDAO.editar(estado);

			System.out.println("Registro editado - Depois:");
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

}
