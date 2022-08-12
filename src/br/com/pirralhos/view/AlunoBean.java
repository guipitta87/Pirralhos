package br.com.pirralhos.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;
import br.com.pirralhos.model.entity.Matricula;
import br.com.pirralhos.model.entity.Sexo;

@ManagedBean(name = "alunoBean")
@SessionScoped
public class AlunoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private GenericDAO<Sexo, Integer> sexoDAO = (GenericDAO<Sexo, Integer>) BeanFactory
			.getBean("sexoDAO");
	private GenericDAO<Aluno, Integer> alunoDAO = (GenericDAO<Aluno, Integer>) BeanFactory
			.getBean("alunoDAO");
	private GenericDAO<Matricula, Integer> matriculaDAO = (GenericDAO<Matricula, Integer>) BeanFactory
			.getBean("matriculaDAO");

	private List<Aluno> listaAlunos;
	private Aluno aluno = new Aluno();
	private Sexo sexo;
	private boolean incluiu;
	private StreamedContent foto;
	private StreamedContent fotoExibicao;
	private Integer idsexo;
	private String turmaFromThisAluno;

	public Aluno gravar() {
		this.aluno.setFkSexo(sexo);
		aluno = getAlunoDAO().save(aluno);
		setIncluiu(true);
		this.listaAlunos = null;
		return aluno;
	}

	public Aluno buscarAluno(Integer ra) {
		if (ra != null && ra != 0) {
			List<Aluno> alunos = getAlunoDAO().findBySQL(
					"from Aluno where ra=" + ra);
			if (alunos != null && alunos.size() > 0)
				return alunos.get(0);
		}
		return null;
	}

	public void limpar() {
		setAluno(new Aluno());
		setIncluiu(false);
		setSexo(new Sexo());
	}

	public List<Aluno> getListaAlunos() {
		if (this.listaAlunos == null) {
			this.listaAlunos = this.getAlunoDAO().listAll();
		}
		return listaAlunos;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public GenericDAO<Sexo, Integer> getSexoDAO() {
		return sexoDAO;
	}

	public void setSexoDAO(GenericDAO<Sexo, Integer> sexoDAO) {
		this.sexoDAO = sexoDAO;
	}

	public GenericDAO<Aluno, Integer> getAlunoDAO() {
		return alunoDAO;
	}

	public void setAlunoDAO(GenericDAO<Aluno, Integer> alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String paginaDeMatricula() {
		return "/incluir_matricula";
	}

	// Metodo responsavel pelo upload da foto
	public void carregarFoto(FileUploadEvent event)
			throws FileNotFoundException, IOException {
		FacesMessage msg = new FacesMessage("Sucesso", event.getFile()
				.getFileName() + " foi carregado.");
		FacesContext.getCurrentInstance().addMessage("mensagem", msg);
		byte[] conteudo = event.getFile().getContents();
		String caminho = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("" + "\\fotos\\" + event.getFile().getFileName());
		FileOutputStream fos = new FileOutputStream(caminho);
		fos.write(conteudo);
		fos.close();
		this.aluno.setFoto(event.getFile().getFileName());
	}

	// Metodo responsavel pelo download da foto
	/*
	 * public StreamedContent getFoto() throws IOException { Aluno aluno =
	 * (Aluno) FacesContext.getCurrentInstance()
	 * .getExternalContext().getRequestMap().get("aluno"); String caminho =
	 * FacesContext.getCurrentInstance().getExternalContext() .getRealPath("" +
	 * "\\fotos\\" + aluno.getFoto()); FileInputStream fis = new
	 * FileInputStream(caminho); this.foto = new DefaultStreamedContent(fis,
	 * caminho, aluno.getFoto()); return this.foto;
	 * 
	 * }
	 */

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public GenericDAO<Matricula, Integer> getMatriculaDAO() {
		return matriculaDAO;
	}

	public void setMatriculaDAO(GenericDAO<Matricula, Integer> matriculaDAO) {
		this.matriculaDAO = matriculaDAO;
	}

	public void setIncluiu(boolean incluiu) {
		this.incluiu = incluiu;
	}

	public boolean isIncluiu() {
		return incluiu;
	}

	public void atualizar() {
		this.getAlunoDAO().save(this.aluno);
		this.listaAlunos = null;
	}

	public StreamedContent getFoto() {
		return this.foto;
	}

	public String getTurmaFromThisAluno() {

		List<Matricula> matriculaLocal = this.getMatriculaDAO().listAll();
		List<Aluno> alunoLocal = this.getAlunoDAO().listAll();

		for (Aluno aluno : alunoLocal) {
			for (Matricula mat : matriculaLocal) {
				if (aluno.getRa().equals(mat.getFkRa().getRa())) {
					this.turmaFromThisAluno = mat.getFkTurma().getDescTurma();
				}
			}
		}
		return turmaFromThisAluno;
	}

	public void setTurmaFromThisAluno(String turmaFromThisAluno) {
		this.turmaFromThisAluno = turmaFromThisAluno;
	}

	public void carregarFotoAction() throws FileNotFoundException {
		Aluno aluno = (Aluno) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("aluno");
		String caminho = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("" + "\\fotos\\" + aluno.getFoto());
		FileInputStream fis = new FileInputStream(caminho);
		this.foto = new DefaultStreamedContent(fis, caminho, aluno.getFoto());
	}

	public void excluir() {

		/* Verifica se o Aluno possui uma Matricula, se sim, não faz a exlusão */
		List<Matricula> matriculaLocal = this.matriculaDAO.listAll();
		for (Matricula mat : matriculaLocal) {
			if (mat.getFkRa().getRa().equals(this.getAluno().getRa())) {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Erro! O aluno possui matricula cadastrada.", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
		}
		this.alunoDAO.delete(this.aluno);
		this.listaAlunos = null;
	}

	public Integer getIdsexo() {
		return idsexo;
	}

	public void setIdsexo(Integer idsexo) {
		this.idsexo = idsexo;
	}

}
