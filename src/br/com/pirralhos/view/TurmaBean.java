package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Curso;
import br.com.pirralhos.model.entity.Periodo;
import br.com.pirralhos.model.entity.Professor;
import br.com.pirralhos.model.entity.Serie;
import br.com.pirralhos.model.entity.Turma;
import br.com.pirralhos.view.constants.ViewConstants;
import br.com.pirralhos.view.utils.CollectionsUtils;
import br.com.pirralhos.view.utils.SelectItemUtils;

@ManagedBean(name = "turmaBean")
@ViewScoped
public class TurmaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private GenericDAO<Turma, Integer> turmaDAO = (GenericDAO<Turma, Integer>) BeanFactory
			.getBean("turmaDAO");
	private GenericDAO<Curso, Integer> cursoDAO = (GenericDAO<Curso, Integer>) BeanFactory
			.getBean("cursoDAO");
	private GenericDAO<Serie, Integer> serieDAO = (GenericDAO<Serie, Integer>) BeanFactory
			.getBean("serieDAO");
	private GenericDAO<Periodo, Integer> periodoDAO = (GenericDAO<Periodo, Integer>) BeanFactory
			.getBean("periodoDAO");
	private GenericDAO<Professor, Integer> professorDAO = (GenericDAO<Professor, Integer>) BeanFactory
			.getBean("professorDAO");

	private boolean atualizou;
	private List<Curso> listaCursos;
	private List<Serie> listaSeries;
	private List<Turma> listaTurmas;
	private List<Periodo> listaPeriodos;
	private List<Professor> listaProfessores;
	private DualListModel<Professor> professores;
	private Turma turma;

	private List<SelectItem> selectItensCursos;
	private List<SelectItem> selectItensSeries;
	private List<SelectItem> selectItensPeriodos;
	private List<SelectItem> selectItensProfessores;

	private Integer idProfessor;
	private Integer idPeriodo;
	private Integer idSerie;
	private Integer idCurso;

	private Serie serie;

	public TurmaBean() {
		atualizarTela();
	}

	public String gravar() {
		if(!validarTurma())
			return null;
		
		if(!validaProfessores())
			return null;
		turma.setFkSerie(new Serie(getIdSerie()));
		turma.setFkPeriodo(new Periodo(getIdPeriodo()));
		turma.setProfessorList(professores.getTarget());
		getTurmaDAO().save(getTurma());
		atualizarTela();
		return "consulta_turma";
	}

	private boolean validaProfessores() {
		
		for(Professor professor : professores.getTarget())
		{
			professor = getListaProfessores().get(getListaProfessores().indexOf(professor));
			boolean integral = false;
			boolean matutino = false;
			boolean vespertino = false;
			for(Turma turma :professor.getTurmaList())
			{
				switch (turma.getFkPeriodo().getIdperiodo()) {
				case ViewConstants.PERIODO_INTEGRAL:
					integral = true;
					break;

				case ViewConstants.PERIODO_MATUTINO:
					matutino = true;
					break;
					
				case ViewConstants.PERIODO_VESPERTINO:
					vespertino = true;
					break;
				}
			}
			if(getIdPeriodo() == ViewConstants.PERIODO_INTEGRAL && ( integral || matutino || vespertino) || 
			   getIdPeriodo() == ViewConstants.PERIODO_MATUTINO && ( integral || matutino)	|| 
			   getIdPeriodo() == ViewConstants.PERIODO_VESPERTINO && ( integral || vespertino))
			{
				FacesMessage message = new FacesMessage(
						"Professor "+ professor.getNome() + " indispon&iacute;vel para dar aula no hor&aacute;rio escolhido" );
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage("painel", message);
				return false;
			
			}
		}
		return true;
	}

	public boolean validarTurma()
	{
		if (serie != null && serie.getIdserie() != null
				&& serie.getIdserie() != 0) {
			int qtdProfessores = professores.getTarget().size();
			if (qtdProfessores < serie.getQtdMinimaProfessores()) {
				FacesMessage message = new FacesMessage(
						"Quantidade m&iacute;nima de professores inv&aacute;lida");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage("painel", message);
				return false;
			} else if (qtdProfessores > serie.getQtdMaximaProfessores()) {
				FacesMessage message = new FacesMessage(
						"Quantidade m&aacute;xima de professores inv&aacute;lida");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage("painel", message);
				return false;
			}
		}
		return true;
	}
	public void limpar() {
		setTurma(new Turma());
		setSelectItensSeries(null);
		setIdCurso(null);
		setIdProfessor(null);
		setIdSerie(null);
		setListaSeries(null);
		setIdPeriodo(null);
		setSerie(new Serie());
	}

	public void excluir() {
		getTurmaDAO().delete(getTurma());
		atualizarTela();
	}


	/**
	 * Limpa os campos input e atualiza a lista de telefones da creche
	 * cadastrados
	 */
	private void atualizarTela() {
		limpar();
		setListaCursos(getCursoDAO().listAll());
		setSelectItensCursos(SelectItemUtils.getSelectItems("idcurso",
				"descCurso", getListaCursos()));
		setListaPeriodos(getPeriodoDAO().listAll());
		setSelectItensPeriodos(SelectItemUtils.getSelectItems("idperiodo",
				"descPeriodo", getListaPeriodos()));
		setListaProfessores(getProfessorDAO().listAll());
		professores = new DualListModel<Professor>(getListaProfessores(),
				new ArrayList<Professor>());
		setListaTurmas(getTurmaDAO().listAll());

	}

	public void popularSerie() {
		if (idCurso != null && idCurso != 0) {
			setListaSeries(getSerieDAO().findBySQL(
					"from Serie where fk_curso=" + idCurso));
			setSelectItensSeries(SelectItemUtils.getSelectItems("idserie",
					"descSerie", getListaSeries()));
			List<SelectItem> listaSelectItens = new ArrayList<SelectItem>();
			
			switch(idCurso)
			{
				case ViewConstants.CURSO_BERCARIO:
					listaSelectItens.add(new SelectItem(ViewConstants.PERIODO_INTEGRAL ,"Integral"));
				break;
				
				case ViewConstants.CURSO_MATERNAL:
					listaSelectItens.add(new SelectItem(ViewConstants.PERIODO_INTEGRAL ,"Integral"));
				break;

				case ViewConstants.CURSO_EMEI:
					listaSelectItens.add(new SelectItem(ViewConstants.PERIODO_MATUTINO ,"Matutino"));
					listaSelectItens.add(new SelectItem(ViewConstants.PERIODO_VESPERTINO ,"Vespertino"));
				break;
			}
			
			setSelectItensPeriodos(listaSelectItens);
		} else {
			setListaPeriodos(null);
			setListaSeries(null);
			setSelectItensSeries(null);
			setIdSerie(null);
			setSerie(null);
		}
		atualizarSerie();
	}

	public GenericDAO<Turma, Integer> getTurmaDAO() {
		return turmaDAO;
	}

	public void setTurmaDAO(GenericDAO<Turma, Integer> turmaDAO) {
		this.turmaDAO = turmaDAO;
	}

	public GenericDAO<Curso, Integer> getCursoDAO() {
		return cursoDAO;
	}

	public void setCursoDAO(GenericDAO<Curso, Integer> cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	public GenericDAO<Serie, Integer> getSerieDAO() {
		return serieDAO;
	}

	public void setSerieDAO(GenericDAO<Serie, Integer> serieDAO) {
		this.serieDAO = serieDAO;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<Serie> getListaSeries() {
		return listaSeries;
	}

	public void setListaSeries(List<Serie> listaSeries) {
		this.listaSeries = listaSeries;
	}

	public List<Turma> getListaTurmas() {
		if (listaTurmas == null)
			listaTurmas = getTurmaDAO().listAll();
		return listaTurmas;
	}

	public void setListaTurmas(List<Turma> listaTurmas) {
		this.listaTurmas = listaTurmas;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Periodo> getListaPeriodos() {
		return listaPeriodos;
	}

	public void setListaPeriodos(List<Periodo> listaPeriodos) {
		this.listaPeriodos = listaPeriodos;
	}

	public List<SelectItem> getSelectItensCursos() {
		return selectItensCursos;
	}

	public void setSelectItensCursos(List<SelectItem> selectItensCursos) {
		this.selectItensCursos = selectItensCursos;
	}

	public List<SelectItem> getSelectItensSeries() {
		return selectItensSeries;
	}

	public void setSelectItensSeries(List<SelectItem> selectItensSeries) {
		this.selectItensSeries = selectItensSeries;
	}

	public GenericDAO<Periodo, Integer> getPeriodoDAO() {
		return periodoDAO;
	}

	public void setPeriodoDAO(GenericDAO<Periodo, Integer> periodoDAO) {
		this.periodoDAO = periodoDAO;
	}

	public List<SelectItem> getSelectItensPeriodos() {
		return selectItensPeriodos;
	}

	public void setSelectItensPeriodos(List<SelectItem> selectItensPeriodos) {
		this.selectItensPeriodos = selectItensPeriodos;
	}

	public GenericDAO<Professor, Integer> getProfessorDAO() {
		return professorDAO;
	}

	public void setProfessorDAO(GenericDAO<Professor, Integer> professorDAO) {
		this.professorDAO = professorDAO;
	}

	public List<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}

	public List<SelectItem> getSelectItensProfessores() {
		return selectItensProfessores;
	}

	public void setSelectItensProfessores(
			List<SelectItem> selectItensProfessores) {
		this.selectItensProfessores = selectItensProfessores;
	}

	public Integer getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Integer idProfessor) {
		this.idProfessor = idProfessor;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public Integer getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(Integer idSerie) {
		this.idSerie = idSerie;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	// Metodo responsabel por atuliazar as informacoes da tela consulta_turma
	public void atualiza() {
		serie = CollectionsUtils.getObject(listaSeries,new Serie(idSerie)); 
		if(!validarTurma())
			return ;
		Periodo periodo = this.getPeriodoDAO().findById(this.idPeriodo);
		this.turma.setFkPeriodo(periodo);
		turma.setProfessorList(professores.getTarget());
		this.turma.setFkSerie(serie);
		this.getTurmaDAO().save(this.turma);
		setAtualizou(true);
		atualizarTela();
	}

	public DualListModel<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(DualListModel<Professor> professores) {
		this.professores = professores;
	}

	public void atualizarSerie() {
		if (!(listaSeries == null) && !(idSerie == null))
			serie = CollectionsUtils.getObject(listaSeries, new Serie(idSerie));
		else
			serie = null;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public void atualizarDadosTurma() {
		setIdPeriodo(turma.getFkPeriodo().getIdperiodo());
		setIdSerie(turma.getFkSerie().getIdserie());
		setIdCurso(turma.getFkSerie().getFkCurso().getIdcurso());
		popularSerie();
		professores.setTarget(new ArrayList<Professor>());
		professores.getTarget().addAll(turma.getProfessorList());
		for (int i = 0; i < professores.getTarget().size(); i++) {
			professores.getSource().remove(this.professores.getTarget().get(i));
		}
		setAtualizou(false);

	}

	public boolean isAtualizou() {
		return atualizou;
	}

	public void setAtualizou(boolean atualizou) {
		this.atualizou = atualizou;
	}
	
}
