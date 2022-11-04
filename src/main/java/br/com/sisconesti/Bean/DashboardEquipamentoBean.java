package br.com.sisconesti.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import br.com.sisconesti.dao.EquipamentoDAO;
import br.com.sisconesti.domain.Equipamento;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class DashboardEquipamentoBean implements Serializable {

	private PieChartModel pieModel;

	private List<Equipamento> lista;

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
	
	public List<Equipamento> getLista() {
		return lista;
	}
	
	public void setLista(List<Equipamento> lista) {
		this.lista = lista;
	}
	
	@PostConstruct
	public void listar() {

		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();

		try {

			lista = equipamentoDAO.listar();
			grafico(lista);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void grafico(List<Equipamento> lista) {

		
		pieModel = new PieChartModel();
		
		for(Equipamento equip : lista) {
			
			pieModel.set(equip.getTipo(), equip.getId());
			
		}
			
			pieModel.setTitle("Equipamentos por Tipo");
			pieModel.setLegendPosition("e");
			pieModel.setFill(false);
			pieModel.setShowDataLabels(true);
			pieModel.setDiameter(250);
		
			
		
		
	}

}
