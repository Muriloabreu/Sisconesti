package br.com.sisconesti.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.sisconesti.Bean.AutenticacaoBean;
import br.com.sisconesti.domain.Usuario;

public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {
		
		String paginaAtual = Faces.getViewId(); // server para capiturar o Id da pagina atual
		
		
		
		boolean pagAutenticada = paginaAtual.contains("login.xhtml"); 
		
		if(!pagAutenticada) {
			
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		
			if(autenticacaoBean == null ) {
				
				Faces.navigate("/pages/login.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			
			if(usuario == null) {
				
				Faces.navigate("/pages/login.xhtml");
				return;
			}
			
		}
		
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
