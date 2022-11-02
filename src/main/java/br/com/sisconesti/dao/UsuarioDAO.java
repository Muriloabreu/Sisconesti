package br.com.sisconesti.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.sisconesti.domain.Usuario;
import br.com.sisconesti.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	
	public Usuario autenticar(String email, String senha) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			consulta.add(Restrictions.eq("p.email", email));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
			
			
		} catch (RuntimeException e) {
			
			throw e;			
		
		}
		finally {
			
			sessao.close();
		}
		
	}

}
