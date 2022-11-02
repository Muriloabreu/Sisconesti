package br.com.sisconesti.Bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sisconesti.dao.EstadoDAO;
import br.com.sisconesti.domain.Estado;
import br.com.sisconesti.dao.CidadeDAO;
import br.com.sisconesti.dao.EmpresaDAO;
import br.com.sisconesti.domain.Cidade;
import br.com.sisconesti.domain.Empresa;


@ManagedBean
@ViewScoped
public class EmpresaBean {

	private Empresa empresa;
	private List<Empresa> empresas;
	private Estado estado;
	private List<Estado> estados;	
	private List<Cidade> cidades;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct
	public void listar() {

		try {

			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as empresas");
			erro.printStackTrace();
		}

	}

	public void novo() {

		try {

			empresa = new Empresa();
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<Cidade>();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova empresa");
			erro.printStackTrace();
		}
	}

	public void salvar() {

		try {

			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresaDAO.merge(empresa);

			empresa = new Empresa();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();
			
			empresas = empresaDAO.listar();

			Messages.addGlobalInfo("Empresa salva com sucesso");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova empresa");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {

		try {

			empresa = (Empresa) evento.getComponent().getAttributes().get("empresaSelecionada");

			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresaDAO.excluir(empresa);

			empresas = empresaDAO.listar();

			Messages.addGlobalInfo("Empresa removida com sucesso");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a empresa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {

			empresa = (Empresa) evento.getComponent().getAttributes().get("empresaSelecionada");

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
			

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();
		}
	}

	public void popular() {

		try {

			if (estado != null) {

				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getId());

			} else {

				cidades = new ArrayList<>();
			}
			
		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			erro.printStackTrace();
		}
	}

}
