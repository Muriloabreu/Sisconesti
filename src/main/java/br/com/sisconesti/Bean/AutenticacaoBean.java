package br.com.sisconesti.Bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.sisconesti.dao.UsuarioDAO;
import br.com.sisconesti.domain.Pessoa;
import br.com.sisconesti.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private Usuario usuario;
	private Usuario usuarioLogado;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	@PostConstruct
	public void iniciar() {
		
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	
	}
	
	public void autenticar() {
		
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getEmail(), usuario.getSenha());
			
			if(usuarioLogado == null) {
				
				Messages.addFlashGlobalError("E-mail ou senha incorretos!");
				return;
			}
			
			Faces.redirect("./pages/dashboard.xhtml");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	public boolean temPermissoes(List<String> permissoes) {
		
		for (String permissao : permissoes) {
			
			if(usuarioLogado.getTipo().equals(permissao.charAt(0))) {
				
				return true;
				
			}
		}
		return false;
	}

}
