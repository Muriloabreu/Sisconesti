package br.com.sisconesti.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sisconesti.dao.CidadeDAO;
import br.com.sisconesti.dao.EmpresaDAO;
import br.com.sisconesti.dao.EquipamentoDAO;
import br.com.sisconesti.dao.FabricanteDAO;
import br.com.sisconesti.dao.FuncionarioDAO;
import br.com.sisconesti.dao.PessoaDAO;
import br.com.sisconesti.dao.SetorDAO;
import br.com.sisconesti.domain.Empresa;
import br.com.sisconesti.domain.Equipamento;
import br.com.sisconesti.domain.Fabricante;
import br.com.sisconesti.domain.Funcionario;
import br.com.sisconesti.domain.SetorEmpresa;


@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class EquipamentoBean implements Serializable{
	
	private Equipamento equipamento;
	private Funcionario funcionario;
	private Empresa empresa;
	
	private List<Equipamento> equipamentos;
	private List<Funcionario> funcionarios;
	private List<Empresa> empresas;
	private List<SetorEmpresa> setores;
	private List<Fabricante> fabricantes;
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	
	public List<SetorEmpresa> getSetores() {
		return setores;
	}

	public void setSetores(List<SetorEmpresa> setores) {
		this.setores = setores;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	

	@PostConstruct
	public void listar() {

		try {
			
			EquipamentoDAO equipDAO = new EquipamentoDAO();
			equipamentos = equipDAO.listar();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();


		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os Equipamentos");
			erro.printStackTrace();
		}

	}

	public void novo() {

		try {
			
			equipamento = new Equipamento();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();


		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo Equipamento");
			erro.printStackTrace();
		}
	}

	public void salvar() {

		try {
			
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentoDAO.merge(equipamento);
			

			equipamento = new Equipamento();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();

			equipamentos = equipamentoDAO.listar();


			Messages.addGlobalInfo("Equipamento salvo com sucesso");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um Equipamento");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {

		try {
			
			equipamento = (Equipamento) evento.getComponent().getAttributes().get("equipamentoSelecionado");
			
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentoDAO.excluir(equipamento);
			
			
			equipamentos = equipamentoDAO.listar();

			Messages.addGlobalInfo("Equipamento removido com sucesso");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Equipamento");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			
			equipamento = (Equipamento) evento.getComponent().getAttributes().get("equipamentoSelecionado");
			
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentos = equipamentoDAO.listar();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Equipamento");
			erro.printStackTrace();
		}
	}
	
	
	public void popular() {

		try {

			if (funcionario != null) {

				EmpresaDAO empresaDAO = new EmpresaDAO();
				empresas = empresaDAO.buscarPorFuncionario(funcionario.getId());
				
				SetorDAO setorDAO = new SetorDAO();
				setores = setorDAO.buscarPorFuncionarioSetor(funcionario.getId());
				
				

			} else {

				empresas = new ArrayList<>();
				setores = new ArrayList<>();
			}
			
		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as empresas");
			erro.printStackTrace();
		}
	}
	
	

}
