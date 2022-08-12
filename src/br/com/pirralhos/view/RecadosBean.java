package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.cfg.FkSecondPass;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;
import br.com.pirralhos.model.entity.Professor;
import br.com.pirralhos.model.entity.Recado;
import br.com.pirralhos.model.entity.Responsavel;
import br.com.pirralhos.model.entity.Turma;
import br.com.pirralhos.view.constants.ViewConstants;
import br.com.pirralhos.view.utils.CollectionsUtils;

@ManagedBean(name = "recadosBean")
@SessionScoped
public class RecadosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private GenericDAO<Recado, Integer> recadoDAO = (GenericDAO<Recado, Integer>) BeanFactory
			.getBean("recadoDAO");
	private GenericDAO<Responsavel, Integer> responsavelDAO = (GenericDAO<Responsavel, Integer>) BeanFactory
			.getBean("responsavelDAO");
	private GenericDAO<Professor, Integer> professorDAO = (GenericDAO<Professor, Integer>) BeanFactory
			.getBean("professorDAO");
	private List<Responsavel> listaResponsaveis;
	private List<SelectItem> selectItensResponsaveis;
	private Recado recado;
	@ManagedProperty(value = "#{smsBean}")
	private SMSBean smsBean;

	public RecadosBean() {
		limpar();
	}

	public void limpar() {
		setRecado(new Recado());
		getRecado().setFkResponsavel(new Responsavel());
		getRecado().setFkProfessor(new Professor());
	}

	public String iniciarEnvioRecadoProfessor(Aluno aluno, Professor professor) {
		atualizarTela();
		getRecado().setFkAluno(aluno);
		getRecado().setFkProfessor(professor);
		getRecado().setQuemEnviou(ViewConstants.RECADO_PROFESSOR);
		return "enviar_recado_professor";
	}

	public String iniciarRecadosResponsavel(Aluno aluno, Responsavel responsavel) {
		atualizarTela();
		getRecado().setFkAluno(aluno);
		getRecado().setFkResponsavel(responsavel);
		getRecado().setQuemEnviou(ViewConstants.RECADO_RESPONSAVEL);
		return "enviar_recado_responsavel";
	}

	public List<Recado> recadosRecebidosProfessor(Professor professor,
			Turma turma) {

		List<Recado> listaRecados = getRecadoDAO().findBySQL(
				"from Recado where fkProfessor.idprofessor='"
						+ professor.getIdprofessor() + "' and quemEnviou!='"
						+ ViewConstants.RECADO_PROFESSOR + "'");
		return filtrarRecadosPorTurma(listaRecados, turma);

	}

	public List<Recado> recadosEnviadosProfessor(Professor professor,
			Turma turma) {
		List<Recado> listaRecados = getRecadoDAO().findBySQL(
				"from Recado where fkProfessor.idprofessor='"
						+ professor.getIdprofessor() + "' and quemEnviou='"
						+ ViewConstants.RECADO_PROFESSOR + "'");
		return filtrarRecadosPorTurma(listaRecados, turma);
	}

	public List<Recado> filtrarRecadosPorTurma(List<Recado> recados, Turma turma) {
		List<Recado> listaRecadosTurma = new ArrayList<Recado>();
		for (Recado recado : recados) {
			if (recado.getFkAluno().getMatriculaAtual().getFkTurma()
					.getIdturma().equals(turma.getIdturma())) {
				listaRecadosTurma.add(recado);
			}
		}
		return listaRecadosTurma;
	}

	public List<Recado> recadosRecebidosResponsavel(Responsavel responsavel,
			Aluno aluno) {
		return getRecadoDAO().findBySQL(
				"from Recado where fkResponsavel.idresponsavel='"
						+ responsavel.getIdresponsavel()
						+ "' and quemEnviou!='"
						+ ViewConstants.RECADO_RESPONSAVEL + "'");
	}

	public List<Recado> recadosEnviadosResponsavel(Responsavel responsavel,
			Aluno aluno) {
		return getRecadoDAO().findBySQL(
				"from Recado where fkResponsavel.idresponsavel='"
						+ responsavel.getIdresponsavel() + "' and quemEnviou='"
						+ ViewConstants.RECADO_RESPONSAVEL + "'");

	}

	public String gravar() {
		getRecado().setDataRecado(new Date());
		

		if (recado.getQuemEnviou() == ViewConstants.RECADO_PROFESSOR) {
			String numero = ((Responsavel) CollectionsUtils.getObject(
					responsavelDAO.listAll(), new Responsavel(recado
							.getFkResponsavel().getIdresponsavel())))
					.getTelefoneCelular();
			boolean enviou = enviarSms(numero);
			if (!enviou)
				return "enviar_recado_professor";
			getRecadoDAO().save(recado);
			atualizarTela();
			return "consulta_recados_professor";
		} else {
			String numero = ((Professor) CollectionsUtils.getObject(
					professorDAO.listAll(), new Professor(recado
							.getFkProfessor().getIdprofessor())))
					.getFkTelefoneCreche().getNumero();
			boolean enviou = enviarSms(numero);
			if (!enviou)
				return "enviar_recado_responsavel";
			getRecadoDAO().save(recado);
			atualizarTela();
			return "dashboard_responsavel";
		}
	}

	public boolean enviarSms(String numero) {
		numero = numero.replace("-", "");
		numero = numero.replace("(", "");
		numero = numero.replace(")", "");
		Object[] response = smsBean.enviarSMS(numero, recado.getDescRecado());
		if(response == null)
		{
			FacesMessage message = new FacesMessage("ERRO AO ENVIAR A MENSAGEM");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("envia_recado",
					message);
			return false;
		}
		if (response !=null && Boolean.valueOf(response[0].toString()) == false) {
			FacesMessage message = new FacesMessage(response[1].toString());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("envia_recado",
					message);
			return false;
		}
		return true;
	}

	private void atualizarTela() {
		limpar();
	}

	public List<Recado> obterRecadosProfessor(Aluno aluno) {
		return getRecadoDAO().findBySQL(
				"from Recado where fkAluno.ra=" + aluno.getRa()
						+ " and quemEnviou='" + ViewConstants.RECADO_PROFESSOR
						+ "'");
	}

	public List<Recado> obterRecadosResponsavel(Aluno aluno) {
		return getRecadoDAO().findBySQL(
				"from Recado where fkAluno.ra=" + aluno.getRa()
						+ " and quemEnviou='"
						+ ViewConstants.RECADO_RESPONSAVEL + "'");
	}

	public GenericDAO<Responsavel, Integer> getResponsavelDAO() {
		return responsavelDAO;
	}

	public void setResponsavelDAO(
			GenericDAO<Responsavel, Integer> responsavelDAO) {
		this.responsavelDAO = responsavelDAO;
	}

	public List<Responsavel> getListaResponsaveis() {
		return listaResponsaveis;
	}

	public void setListaResponsaveis(List<Responsavel> listaResponsaveis) {
		this.listaResponsaveis = listaResponsaveis;
	}

	public List<SelectItem> getSelectItensResponsaveis() {
		return selectItensResponsaveis;
	}

	public void setSelectItensResponsaveis(
			List<SelectItem> selectItensResponsaveis) {
		this.selectItensResponsaveis = selectItensResponsaveis;
	}

	public GenericDAO<Recado, Integer> getRecadoDAO() {
		return recadoDAO;
	}

	public void setRecadoDAO(GenericDAO<Recado, Integer> recadoDAO) {
		this.recadoDAO = recadoDAO;
	}

	public Recado getRecado() {
		return recado;
	}

	public void setRecado(Recado recado) {
		this.recado = recado;
	}

	public SMSBean getSmsBean() {
		return smsBean;
	}

	public void setSmsBean(SMSBean smsBean) {
		this.smsBean = smsBean;
	}

	public GenericDAO<Professor, Integer> getProfessorDAO() {
		return professorDAO;
	}

	public void setProfessorDAO(GenericDAO<Professor, Integer> professorDAO) {
		this.professorDAO = professorDAO;
	}

}
