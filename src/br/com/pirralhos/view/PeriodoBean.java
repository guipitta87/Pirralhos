package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;
import br.com.pirralhos.model.entity.Matricula;
import br.com.pirralhos.model.entity.Periodo;
import br.com.pirralhos.model.entity.Sexo;

@ManagedBean(name = "periodoBean")
@ViewScoped
public class PeriodoBean implements Serializable {
	private GenericDAO<Periodo, Integer> periodoDAO = (GenericDAO<Periodo, Integer>) BeanFactory
			.getBean("periodoDAO");
	private Periodo periodo;
	private List<Periodo> listaPeriodos;

	public List<Periodo> getListaPeriodos() {
		return this.getPeriodoDAO().listAll();
	}

	public void setListaPeriodos(List<Periodo> listaPeriodos) {
		this.listaPeriodos = listaPeriodos;
	}

	public GenericDAO<Periodo, Integer> getPeriodoDAO() {
		return periodoDAO;
	}

	public void setPeriodoDAO(GenericDAO<Periodo, Integer> periodoDAO) {
		this.periodoDAO = periodoDAO;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public void gravar() {
		getPeriodoDAO().save(periodo);
	}

}
