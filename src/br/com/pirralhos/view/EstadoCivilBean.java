package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.EstadoCivil;

@ManagedBean(name="estadoCivilBean")
public class EstadoCivilBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private GenericDAO<EstadoCivil, Integer> estadoCivilDAO = (GenericDAO<EstadoCivil, Integer>) BeanFactory.getBean("estadoCivilDAO");
	private List<EstadoCivil> listaEstadoCivis;
	public GenericDAO<EstadoCivil, Integer> getEstadoCivilDAO() {
		return estadoCivilDAO;
	}
	public void setEstadoCivilDAO(GenericDAO<EstadoCivil, Integer> estadoCivilDAO) {
		this.estadoCivilDAO = estadoCivilDAO;
	}
	public List<EstadoCivil> getListaEstadoCivis() {
		if(listaEstadoCivis == null)
			listaEstadoCivis = getEstadoCivilDAO().listAll();
		return listaEstadoCivis;
	}
	public void setListaEstadoCivis(List<EstadoCivil> listaEstadoCivis) {
		this.listaEstadoCivis = listaEstadoCivis;
	}
	
}
