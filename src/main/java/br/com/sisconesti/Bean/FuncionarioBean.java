package br.com.sisconesti.Bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sisconesti.dao.EmpresaDAO;
import br.com.sisconesti.dao.FuncionarioDAO;
import br.com.sisconesti.dao.PessoaDAO;
import br.com.sisconesti.dao.SetorDAO;
import br.com.sisconesti.domain.Empresa;
import br.com.sisconesti.domain.Funcionario;
import br.com.sisconesti.domain.Pessoa;
import br.com.sisconesti.domain.SetorEmpresa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean {
	
	private Funcionario funcionario;	
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;
	private List<Empresa> empresas;
	private List<SetorEmpresa> setores;
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
		
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	public List<SetorEmpresa> getSetores() {
		return setores;
	}
	public void setSetores(List<SetorEmpresa> setores) {
		this.setores = setores;
	}
	
	
	@PostConstruct
	public void listar() {

		try {
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os funcionários");
			erro.printStackTrace();
		}

	}

	public void novo() {

		try {
			
			funcionario = new Funcionario();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo funcionário");
			erro.printStackTrace();
		}
	}

	public void salvar() {

		try {
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);
					
			funcionario = new Funcionario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();

			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionário salvo com sucesso");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um funcionário");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {

		try {
			
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("fucnionarioSelecionado");
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);
			

			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionário removido com sucesso");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Funcionário");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("fucnionarioSelecionado");
			

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um funcionário");
			erro.printStackTrace();
		}
	}
	
	
	

}
