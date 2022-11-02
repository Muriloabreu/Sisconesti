package br.com.sisconesti.Bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sisconesti.dao.EstadoDAO;
import br.com.sisconesti.dao.PessoaDAO;
import br.com.sisconesti.domain.Estado;
import br.com.sisconesti.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean {

	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void novo() {

		pessoa = new Pessoa();

	}

	public void salvar() {

		try {

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);

			novo();

			pessoas = pessoaDAO.listar("first_nome");
			Messages.addGlobalInfo("Pessoa salvo com sucesso");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar salvar pessoa");
			erro.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {

		try {

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}

	}
	
	public void excluir(ActionEvent evento) {

		try {

			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);

			pessoas = pessoaDAO.listar();

			Messages.addGlobalInfo("Pessoa removida com sucesso");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover Pessoa");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
	}

}
