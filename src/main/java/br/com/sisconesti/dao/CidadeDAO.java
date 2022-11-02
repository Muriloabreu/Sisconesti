package br.com.sisconesti.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sisconesti.util.HibernateUtil;
import br.com.sisconesti.domain.Cidade;

public class CidadeDAO extends GenericDAO<Cidade> {
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long estadoCodigo) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.id", estadoCodigo));	
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
			
		} catch (RuntimeException erro) {
			
			throw erro;
			
		} finally {
			
			sessao.close();
		}
	}
}
