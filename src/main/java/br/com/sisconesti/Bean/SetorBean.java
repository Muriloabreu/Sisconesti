package br.com.sisconesti.Bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sisconesti.dao.EstadoDAO;
import br.com.sisconesti.dao.SetorDAO;
import br.com.sisconesti.domain.Estado;
import br.com.sisconesti.domain.SetorEmpresa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SetorBean {

	private SetorEmpresa setor;
	private List<SetorEmpresa> setores;

	public SetorEmpresa getSetor() {
		return setor;
	}

	public void setSetor(SetorEmpresa setor) {
		this.setor = setor;
	}

	public List<SetorEmpresa> getSetores() {
		return setores;
	}

	public void setSetores(List<SetorEmpresa> setores) {
		this.setores = setores;
	}

	public void novo() {

		setor = new SetorEmpresa();
	}

	public void salvar() {

		try {

			SetorDAO setorDAO = new SetorDAO();
			setorDAO.merge(setor);

			novo();
			setores = setorDAO.listar("nome");

			
			Messages.addGlobalInfo("Setor salvo com sucesso");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Setor");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {

		try {
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();
			

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar listar os setores");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {

		try {

			setor = (SetorEmpresa) evento.getComponent().getAttributes().get("setorSelecionado");

			SetorDAO setorDAO = new SetorDAO();
			setorDAO.excluir(setor);
			

			setores = setorDAO.listar();
			

			Messages.addGlobalInfo("Setor removido com sucesso");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		setor = (SetorEmpresa) evento.getComponent().getAttributes().get("setorSelecionado");
	}

}
