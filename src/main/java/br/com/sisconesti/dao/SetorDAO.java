package br.com.sisconesti.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import br.com.sisconesti.domain.SetorEmpresa;
import br.com.sisconesti.util.HibernateUtil;

public class SetorDAO extends GenericDAO<SetorEmpresa> {
	
	
	@SuppressWarnings("unchecked")
	public List<SetorEmpresa> buscarPorFuncionarioSetor(Long funcionarioCodigo) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			
			Criteria consulta = sessao.createCriteria(SetorEmpresa.class);
			consulta.add(Restrictions.eq("funcionario.id", funcionarioCodigo));	
			consulta.addOrder(Order.asc("nome"));
			List<SetorEmpresa> resultado = consulta.list();
			return resultado;
			
		} catch (RuntimeException erro) {
			
			throw erro;
			
		} finally {
			
			sessao.close();
		}
	}

}
