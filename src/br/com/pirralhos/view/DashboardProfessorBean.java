package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Matricula;
import br.com.pirralhos.model.entity.Professor;
import br.com.pirralhos.model.entity.Turma;

@ManagedBean(name="dashboardProfessorBean")
@SessionScoped
public class DashboardProfessorBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Professor professor;
	private List<Turma> turmas;
	private Turma turmaSelecionada;
	private GenericDAO<Turma, Integer> turmaDAO = (GenericDAO<Turma, Integer>) BeanFactory
	.getBean("turmaDAO");
	private GenericDAO<Professor, Integer> professorDAO = (GenericDAO<Professor, Integer>) BeanFactory
	.getBean("professorDAO");
	private Matricula matriculaSelecionada;

	public String atualizarTela() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String idUsuario = String.valueOf(fc.getExternalContext().getSessionMap().get("ID_USUARIO"));
		turmas = new ArrayList<Turma>();
		if(idUsuario !=null  && !"".equals(idUsuario))
		{
			List<Professor> profs = getProfessorDAO().findBySQL("from Professor where fkUsuario.idusuario='"+idUsuario+"'");
			if(profs != null && profs.size() == 1)
			{
				professor = profs.get(0);
				for(Turma turma : turmaDAO.listAll())
				{
					if(turma.getProfessorList().contains(professor))
						turmas.add(turma);
				}
			}
		}
		setTurmaSelecionada(null);
		setMatriculaSelecionada(null);
		return "dashboard_professor";
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public GenericDAO<Professor, Integer> getProfessorDAO() {
		return professorDAO;
	}

	public void setProfessorDAO(GenericDAO<Professor, Integer> professorDAO) {
		this.professorDAO = professorDAO;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public Matricula getMatriculaSelecionada() {
		return matriculaSelecionada;
	}

	public void setMatriculaSelecionada(Matricula matriculaSelecionada) {
		this.matriculaSelecionada = matriculaSelecionada;
	}
	
}
