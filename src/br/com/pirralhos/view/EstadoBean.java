package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Cidade;
import br.com.pirralhos.model.entity.Estado;
import br.com.pirralhos.model.entity.Sexo;

@ManagedBean(name = "estadoBean")
@ViewScoped
public class EstadoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenericDAO<Estado, Integer> estadoDAO = (GenericDAO<Estado, Integer>) BeanFactory.getBean("estadoDAO");
	private List<Estado> listaEstados;

	private Estado estado = new Estado();

	public void gravar() {
		getEstadoDAO().save(this.estado);
		this.estado = new Estado();
		this.listaEstados = null;
	}

	public GenericDAO<Estado, Integer> getEstadoDAO() {
		return estadoDAO;
	}

	public void setEstadoDAO(GenericDAO<Estado, Integer> estadoDAO) {
		this.estadoDAO = estadoDAO;
	}

	public List<Estado> getListaEstados() {
		if (this.listaEstados == null) {
			this.listaEstados = this.getEstadoDAO().listAll();
		}
		return this.listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
