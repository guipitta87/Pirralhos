package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;
import br.com.pirralhos.model.entity.Professor;
import br.com.pirralhos.model.entity.Recado;
import br.com.pirralhos.model.entity.Responsavel;
import br.com.pirralhos.model.entity.Turma;

@ManagedBean(name = "dashboardResponsavelBean")
@SessionScoped
public class DashboardResponsavelBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Responsavel responsavel;
	private List<Aluno> alunos; 
	private Aluno alunoSelecionado;
	private GenericDAO<Responsavel, Integer> responsavelDAO = (GenericDAO<Responsavel, Integer>) BeanFactory
			.getBean("responsavelDAO");
	private GenericDAO<Aluno, Integer> alunoDAO = (GenericDAO<Aluno, Integer>) BeanFactory
			.getBean("alunoDAO");

	public DashboardResponsavelBean() {
		atualizarTela();
	}

	public String atualizarTela() {
		FacesContext fc = FacesContext.getCurrentInstance();
		alunos= new ArrayList<Aluno>();
		String idUsuario = String.valueOf(fc.getExternalContext()
				.getSessionMap().get("ID_USUARIO"));
		if (idUsuario != null && !"".equals(idUsuario)) {
			List<Responsavel> resps = getResponsavelDAO().findBySQL(
					"from Responsavel where fkUsuario.idusuario='" + idUsuario
							+ "'");
			if (resps != null && resps.size() == 1) {
				responsavel = resps.get(0);
				for (Aluno aluno : alunoDAO.listAll()) {
					if (aluno.getResponsavelList().contains(responsavel))
						alunos.add(aluno);
				}
			}
		}
		return "dashboard_responsavel";
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public GenericDAO<Responsavel, Integer> getResponsavelDAO() {
		return responsavelDAO;
	}

	public void setResponsavelDAO(
			GenericDAO<Responsavel, Integer> responsavelDAO) {
		this.responsavelDAO = responsavelDAO;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public GenericDAO<Aluno, Integer> getAlunoDAO() {
		return alunoDAO;
	}

	public void setAlunoDAO(GenericDAO<Aluno, Integer> alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}
	
}
