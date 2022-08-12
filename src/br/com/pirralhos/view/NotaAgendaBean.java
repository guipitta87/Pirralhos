package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;
import br.com.pirralhos.model.entity.LctoAgenda;
import br.com.pirralhos.model.entity.Matricula;
import br.com.pirralhos.model.entity.Turma;

@ManagedBean(name="notaAgendaBean")
@ViewScoped
public class NotaAgendaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private GenericDAO<LctoAgenda,Integer> lctoAgendaDAO = (GenericDAO<LctoAgenda,Integer>) BeanFactory.getBean("cursoDAO");
	private LctoAgenda lctoAgenda;
	private List<LctoAgenda> lctosAgenda;
	@ManagedProperty(value="#{dashboardProfessorBean.turmaSelecionada}")
	private Turma turma;
	//
	public List<LctoAgenda> consultarNotasAgenda(Aluno aluno)
	{
		List<Matricula> matriculas = aluno.getMatriculaList();
		if(matriculas !=null && matriculas.size()>0)
			return getLctoAgendaDAO().findBySQL("from LctoAgenda where fkTurma.idturma="+ matriculas.get(matriculas.size()-1).getFkTurma().getIdturma());
		return null;
	}
	public NotaAgendaBean()
	{
		atualizarTela();
	}
	public void gravar()
	{
		lctoAgenda.setFkTurma(turma);
		getLctoAgendaDAO().save(lctoAgenda);
		atualizarTela();
	}
	
	public void excluir()
	{
		getLctoAgendaDAO().delete(lctoAgenda);
		atualizarTela();
	}
	public void limpar()
	{
		setLctoAgenda(new LctoAgenda());
		setLctosAgenda(null);
	}
	private void atualizarTela()
	{
		limpar();
	}
	public GenericDAO<LctoAgenda, Integer> getLctoAgendaDAO() {
		return lctoAgendaDAO;
	}
	public void setLctoAgendaDAO(GenericDAO<LctoAgenda, Integer> lctoAgendaDAO) {
		this.lctoAgendaDAO = lctoAgendaDAO;
	}
	public LctoAgenda getLctoAgenda() {
		return lctoAgenda;
	}
	public void setLctoAgenda(LctoAgenda lctoAgenda) {
		this.lctoAgenda = lctoAgenda;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public List<LctoAgenda> getLctosAgenda() {
		if(lctosAgenda == null && turma !=null)
		{
			lctosAgenda = getLctoAgendaDAO().findBySQL("from LctoAgenda where fkTurma.idturma ="+turma.getIdturma());	
		}	
		return lctosAgenda;
	}
	public void setLctosAgenda(List<LctoAgenda> lctosAgenda) {
		this.lctosAgenda = lctosAgenda;
	}
	
}
