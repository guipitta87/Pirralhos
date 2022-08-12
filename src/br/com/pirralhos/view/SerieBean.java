package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Serie;
import br.com.pirralhos.model.entity.Sexo;

@ManagedBean(name = "serieBean")
@ViewScoped
public class SerieBean implements Serializable {

	private GenericDAO<Serie, Integer> serieDAO = (GenericDAO<Serie, Integer>) BeanFactory
			.getBean("serieDAO");

	private List<Serie> listaSeries;
	private Serie serie = new Serie();

	public GenericDAO<Serie, Integer> getSerieDAO() {
		return serieDAO;
	}

	public void setSerieDAO(GenericDAO<Serie, Integer> serieDAO) {
		this.serieDAO = serieDAO;
	}

	public List<Serie> getListaSeries() {
		return this.getSerieDAO().listAll();
	}

	public void setListaAlunos(List<Serie> listaSeries) {
		this.listaSeries = listaSeries;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public void gravar() {
		getSerieDAO().save(serie);
	}

}
