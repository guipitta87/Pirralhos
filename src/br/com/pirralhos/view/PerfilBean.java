package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Perfil;
import br.com.pirralhos.model.entity.Sexo;

@ManagedBean(name = "perfilBean")
@ViewScoped
public class PerfilBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenericDAO<Perfil, Integer> perfilDAO = (GenericDAO<Perfil, Integer>) BeanFactory.getBean("perfilDAO");
	private List<Perfil> listaPerfis;

	private Perfil perfil = new Perfil();

	public void gravar() {
		getPerfilDAO().save(this.perfil);
		this.perfil = new Perfil();
		this.listaPerfis = null;
	}

	public GenericDAO<Perfil, Integer> getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(GenericDAO<Perfil, Integer> perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	public List<Perfil> getListaPerfis() {
		if (this.listaPerfis == null) {
			this.listaPerfis = this.getPerfilDAO().listAll();
		}
		return this.listaPerfis;
	}

	public void setListaPerfis(List<Perfil> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
