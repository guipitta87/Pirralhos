package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Sexo;

@ManagedBean(name = "sexoBean")
@ViewScoped
public class SexoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenericDAO<Sexo, Integer> sexoDAO = (GenericDAO<Sexo, Integer>) BeanFactory
			.getBean("sexoDAO");
	private List<Sexo> listaSexos;

	private Sexo sexo = new Sexo();

	public void gravar() {
		getSexoDAO().save(this.sexo);
		this.sexo = new Sexo();
		this.listaSexos = null;
	}

	public GenericDAO<Sexo, Integer> getSexoDAO() {
		return sexoDAO;
	}

	public void setSexoDAO(GenericDAO<Sexo, Integer> sexoDAO) {
		this.sexoDAO = sexoDAO;
	}

	public List<Sexo> getListaSexos() {
		if (this.listaSexos == null) {
			this.listaSexos = this.getSexoDAO().listAll();
		}
		return listaSexos;
	}

	public void setListaSexos(List<Sexo> listaSexos) {
		this.listaSexos = listaSexos;
	}

	public String paginaDeMatricula() {
		return "/incluir_matricula";
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

}
