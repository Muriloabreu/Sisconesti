package br.com.sisconesti.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.sisconesti.domain.Pessoa;
import br.com.sisconesti.domain.Usuario;

public class UsuarioDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);
		
		System.out.println("Pessoa Encontrada");
		System.out.println("Nome: " + pessoa.getFirst_nome());
		System.out.println("Email: " + pessoa.getEmail());
		
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhaSemCriptografia("123456");
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		
		usuario.setSenha(hash.toHex());
		usuario.setTipo('A');
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("Usuário salvo com sucesso.");
	}
	
	@Test
	@Ignore
	public void autenticar() {
		
		String email = "murilo.nascimento@tivit.com";
		String senha = "123456";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(email, senha);
		
		System.out.println("Usuário autentica: " + usuario);
	}
	
	@Test
	@Ignore
	public void listar() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		List<Usuario> resultado = usuarioDAO.listar();
		
		
		for(Usuario usuario : resultado) {
			
			
			
		}
		
	}

}
