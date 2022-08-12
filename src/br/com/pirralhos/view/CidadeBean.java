package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Cidade;
import br.com.pirralhos.model.entity.Estado;

@ManagedBean(name = "cidadeBean")
@ViewScoped
public class CidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private GenericDAO<Cidade, Integer> cidadeDAO = (GenericDAO<Cidade, Integer>) BeanFactory.getBean("cidadeDAO");
	private GenericDAO<Estado, Integer> estadoDAO = (GenericDAO<Estado, Integer>) BeanFactory.getBean("estadoDAO");
	private List<Cidade> listaCidades;

	private Cidade cidade = new Cidade();
	private Integer idestado;

	public void gravar() {
		Estado fkEstado = this.getEstadoDAO().findById(this.idestado);
		this.cidade.setIdestado(fkEstado);
		
		getCidadeDAO().save(this.cidade);
		
		this.cidade = new Cidade();
		this.listaCidades = null;
	}

	public void buscarCidade(Integer idEstado)
	{
		if (idEstado != null && idEstado != 0) {
			setListaCidades(getCidadeDAO().findBySQL(
					"from Cidade where idestado=" + idEstado));
		
		} else {
			setListaCidades(null);
		}
	}
	public GenericDAO<Cidade, Integer> getCidadeDAO() {
		return cidadeDAO;
	}

	public void setCidadeDAO(GenericDAO<Cidade, Integer> cidadeDAO) {
		this.cidadeDAO = cidadeDAO;
	}

	public List<Cidade> getListaCidades() {
		return this.listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Integer getIdestado() {
		return idestado;
	}

	public void setIdestado(Integer idestado) {
		this.idestado = idestado;
	}

	public GenericDAO<Estado, Integer> getEstadoDAO() {
		return estadoDAO;
	}

	public void setEstadoDAO(GenericDAO<Estado, Integer> estadoDAO) {
		this.estadoDAO = estadoDAO;
	}

}
