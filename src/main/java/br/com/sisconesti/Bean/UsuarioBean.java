package br.com.sisconesti.Bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.sisconesti.dao.UsuarioDAO;
import br.com.sisconesti.dao.EmpresaDAO;
import br.com.sisconesti.dao.EstadoDAO;
import br.com.sisconesti.dao.PessoaDAO;
import br.com.sisconesti.domain.Empresa;
import br.com.sisconesti.domain.Pessoa;
import br.com.sisconesti.domain.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario;
	
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	

	public void novo() {

		try {

			usuario = new Usuario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {

		try {

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}

	}

	public void salvar() {
		
		try {
			
			SimpleHash hash = new SimpleHash("md5", usuario.getSenha() );
			usuario.setSenha(hash.toHex());
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
			
			usuario = new Usuario();
						
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			usuarios = usuarioDAO.listar();
			
			Messages.addGlobalInfo("Usuário salvo com sucesso");
			
		} catch (RuntimeException erro) {
			
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		}
	
	}
	
	
	public void editar(ActionEvent evento) {
		
		try {

			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
			

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um usuário");
			erro.printStackTrace();
		}
		
	}
	
	public void excluir(ActionEvent evento) {
		
		try {

			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);
			
			usuarios = usuarioDAO.listar();


			Messages.addGlobalInfo("Usuário removido com sucesso");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o usuário");
			erro.printStackTrace();
		}
		
	}
}
