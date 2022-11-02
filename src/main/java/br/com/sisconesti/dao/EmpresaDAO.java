package br.com.sisconesti.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import br.com.sisconesti.domain.Empresa;
import br.com.sisconesti.util.HibernateUtil;

public class EmpresaDAO extends GenericDAO<Empresa> {
	
	
	@SuppressWarnings("unchecked")
	public List<Empresa> buscarPorFuncionario(Long funcionarioCodigo) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			
			Criteria consulta = sessao.createCriteria(Empresa.class);
			consulta.add(Restrictions.eq("funcionario.id", funcionarioCodigo));	
			consulta.addOrder(Order.asc("nome"));
			List<Empresa> resultado = consulta.list();
			return resultado;
			
		} catch (RuntimeException erro) {
			
			throw erro;
			
		} finally {
			
			sessao.close();
		}
	}

}
