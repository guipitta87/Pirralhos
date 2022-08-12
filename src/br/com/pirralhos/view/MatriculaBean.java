package br.com.pirralhos.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;
import br.com.pirralhos.model.entity.Autorizado;
import br.com.pirralhos.model.entity.Matricula;
import br.com.pirralhos.model.entity.Responsavel;
import br.com.pirralhos.model.entity.Turma;
import br.com.pirralhos.view.utils.CollectionsUtils;
import br.com.pirralhos.view.utils.DateUtils;

@ManagedBean(name = "matriculaBean")
@SessionScoped
public class MatriculaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private GenericDAO<Aluno, Integer> alunoDAO = (GenericDAO<Aluno, Integer>) BeanFactory
			.getBean("alunoDAO");
	private GenericDAO<Matricula, Integer> matriculaDAO = (GenericDAO<Matricula, Integer>) BeanFactory
			.getBean("matriculaDAO");
	private GenericDAO<Turma, Integer> turmaDAO = (GenericDAO<Turma, Integer>) BeanFactory
			.getBean("turmaDAO");
	private GenericDAO<Responsavel, Integer> responsavelDAO = (GenericDAO<Responsavel, Integer>) BeanFactory
			.getBean("responsavelDAO");
	private GenericDAO<Autorizado, Integer> autorizadoDAO = (GenericDAO<Autorizado, Integer>) BeanFactory
			.getBean("autorizadoDAO");

	private Aluno aluno;
	private Matricula matricula = new Matricula();
	private List<Matricula> listaMatriculas;
	private String tipoAluno;
	private Turma turma;
	@ManagedProperty(value = "#{matriculaSessionBean}")
	private MatriculaSessionBean matriculaSessionBean;
	@ManagedProperty(value = "#{alunoBean}")
	private AlunoBean alunoBean;
	@ManagedProperty(value = "#{responsavelBean}")
	private ResponsavelBean responsavelBean;
	@ManagedProperty(value = "#{autorizadoBean}")
	private AutorizadoBean autorizadoBean;

	private Integer idAluno;
	private Integer idTurma;
	private List<Aluno> listaAlunos;
	private StreamedContent file;

	public void iniciarPagina() throws IOException {
		atualizarTela();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastro_matricula.xhtml");
	}

	public void concluirAluno() {
		setAluno(alunoBean.gravar());
		responsavelBean.gravarListaSessao(getAluno());
		autorizadoBean.gravarListaSessao(getAluno());
		alunoBean.getAluno().setResponsavelList(
				matriculaSessionBean.getListaResponsaveis());
		alunoBean.getAluno().setAutorizadoList(
				matriculaSessionBean.getListaAutorizados());
		setAluno(alunoBean.gravar());
	}

	public void buscarAluno(Integer ra) {
		Aluno aluno = alunoBean.buscarAluno(ra);
		if (aluno == null) {
			FacesMessage msg = new FacesMessage("Aluno com RA:" + ra
					+ " n&atilde;o foi encontrado");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("ra", msg);
			setAluno(new Aluno());
		} else {
			setAluno(aluno);
		}
	}

	public void atualizarTela() {
		setMatricula(new Matricula());
		matricula.setAnoLetivo(2012);
		alunoBean.setIncluiu(Boolean.FALSE);
		getMatricula().setDataMatricula(new Date());
		setTipoAluno("");
		setAluno(new Aluno());
		setIdTurma(null);
		setListaMatriculas(getMatriculaDAO().listAll());
	}

	public void verificaTipoAluno() {
		matriculaSessionBean.setAlunoNovo(tipoAluno != null
				&& tipoAluno.equals("N"));
	}

	public void limparAlunoNovo() {
		if (matriculaSessionBean.isAlunoNovo()) {
			matriculaSessionBean
					.setListaAutorizados(new ArrayList<Autorizado>());
			matriculaSessionBean
					.setListaResponsaveis(new ArrayList<Responsavel>());
			alunoBean.limpar();
		}
	}

	public String gravar() {
		int mesesNascAluno = DateUtils.monthsBetween(new Date(),
				aluno.getDataNasc());
		turma = CollectionsUtils.getObject(getTurmaDAO().listAll(), new Turma(
				idTurma));
		int mesesMinimoTurma = turma.getFkSerie().getIdadeMinimaMeses();
		int mesesMaximoTurma = turma.getFkSerie().getIdadeMaximaMeses();
		if(turma.getQtdAlunos() >= turma.getFkSerie().getFkCurso().getQtdAluno())
		{
			FacesMessage message = new FacesMessage(
					"N&etilde; h&aacute; vagas dispon&iacute;veis nesta turma"
							+ turma.getFkSerie().getDescSerie());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("ra", message);
			return "cadastro_matricula";
		}
		if (mesesNascAluno < mesesMinimoTurma
				|| mesesNascAluno > mesesMaximoTurma) {
			FacesMessage message = new FacesMessage(
					"Aluno n&etilde;o possui idade adequada para participar da s&eacute;rie "
							+ turma.getFkSerie().getDescSerie());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("ra", message);
			return "cadastro_matricula";
		}
		if (matricula.getCaminhoAtestMed() == null
				|| "".equals(matricula.getCaminhoAtestMed())) {
			FacesMessage message = new FacesMessage(
					"Upload do arquivo de atestado m&eacute;dico &eacute; obrigat&oacute;rio");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("ra", message);
			return "cadastro_matricula";
		}

		List<Matricula> matriculasAluno = aluno.getMatriculaList();
		if (matriculasAluno != null) {
			for (Matricula matriculaAluno : matriculasAluno) {
				if (matriculaAluno.getAnoLetivo() == matricula.getAnoLetivo()) {
					FacesMessage message = new FacesMessage(
							"Aluno j&aacute; possui matricula cadastrada no ano letivo: "
									+ matriculaAluno.getAnoLetivo());
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage("ra", message);
					return "cadastro_matricula";
				}

			}
		}

		matricula.setFkTurma(turma);
		matricula.setFkRa(aluno);
		matricula.setFoto3x4(true);
		this.getMatriculaDAO().save(this.matricula);
		atualizarTela();
		return "consulta_matricula";
	}

	public GenericDAO<Aluno, Integer> getAlunoDAO() {
		return alunoDAO;
	}

	public void setAlunoDAO(GenericDAO<Aluno, Integer> alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public GenericDAO<Matricula, Integer> getMatriculaDAO() {
		return matriculaDAO;
	}

	public void setMatriculaDAO(GenericDAO<Matricula, Integer> matriculaDAO) {
		this.matriculaDAO = matriculaDAO;
	}

	public List<Matricula> getListaMatriculas() {
		if (this.listaMatriculas == null) {
			this.listaMatriculas = this.getMatriculaDAO().listAll();
		}
		return this.listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getTipoAluno() {
		return tipoAluno;
	}

	public void setTipoAluno(String tipoAluno) {
		this.tipoAluno = tipoAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void atualizar() {
		Turma fkTurma = this.getTurmaDAO().findById(this.idTurma);
		this.matricula.setFkTurma(fkTurma);
		this.getMatriculaDAO().save(this.matricula);
	}

	public MatriculaSessionBean getMatriculaSessionBean() {
		return matriculaSessionBean;
	}

	public void setMatriculaSessionBean(
			MatriculaSessionBean matriculaSessionBean) {
		this.matriculaSessionBean = matriculaSessionBean;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public AlunoBean getAlunoBean() {
		return alunoBean;
	}

	public void setAlunoBean(AlunoBean alunoBean) {
		this.alunoBean = alunoBean;
	}

	public ResponsavelBean getResponsavelBean() {
		return responsavelBean;
	}

	public void setResponsavelBean(ResponsavelBean responsavelBean) {
		this.responsavelBean = responsavelBean;
	}

	public AutorizadoBean getAutorizadoBean() {
		return autorizadoBean;
	}

	public void setAutorizadoBean(AutorizadoBean autorizadoBean) {
		this.autorizadoBean = autorizadoBean;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public GenericDAO<Autorizado, Integer> getAutorizadoDAO() {
		return autorizadoDAO;
	}

	public void setAutorizadoDAO(GenericDAO<Autorizado, Integer> autorizadoDAO) {
		this.autorizadoDAO = autorizadoDAO;
	}

	public GenericDAO<Responsavel, Integer> getResponsavelDAO() {
		return responsavelDAO;
	}

	public void setResponsavelDAO(
			GenericDAO<Responsavel, Integer> responsavelDAO) {
		this.responsavelDAO = responsavelDAO;
	}

	public GenericDAO<Turma, Integer> getTurmaDAO() {
		return turmaDAO;
	}

	public void setTurmaDAO(GenericDAO<Turma, Integer> turmaDAO) {
		this.turmaDAO = turmaDAO;
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public List<Aluno> getListaAlunos() {
		return listaAlunos = this.getAlunoDAO().listAll();
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	// Metodo responsavel pelo upload do arquivo
	public void carregarArquivo(FileUploadEvent event)
			throws FileNotFoundException, IOException {

		FacesMessage msg = new FacesMessage("Sucesso", event.getFile()
				.getFileName() + " foi carregado.");
		FacesContext.getCurrentInstance().addMessage("teste", msg);

		String caminho = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("" + "\\fotos\\" + event.getFile().getFileName());

		byte[] conteudo = event.getFile().getContents();
		FileOutputStream fos = new FileOutputStream(caminho);
		fos.write(conteudo);
		fos.close();

		this.matricula.setCaminhoAtestMed(event.getFile().getFileName());

	}

	// Metodo responsavel pelo download do arquivo
	public StreamedContent getFile() throws FileNotFoundException {
		Matricula matricula = (Matricula) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("matricula");
		String caminho = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("" + "\\fotos\\" + matricula.getCaminhoAtestMed());
		FileInputStream stream = new FileInputStream(caminho);
		return file = new DefaultStreamedContent(stream, caminho,
				matricula.getCaminhoAtestMed());
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public void excluir() {
		this.getMatriculaDAO().delete(this.matricula);
		this.listaMatriculas = null;
	}

	public void atualizarDadosMatricula() {
		this.setIdAluno(this.matricula.getFkRa().getRa());
		this.setIdTurma(this.matricula.getFkTurma().getIdturma());
	}

}
