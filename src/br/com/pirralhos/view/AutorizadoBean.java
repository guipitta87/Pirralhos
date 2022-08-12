package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;
import br.com.pirralhos.model.entity.Autorizado;
import br.com.pirralhos.model.entity.EstadoCivil;
import br.com.pirralhos.model.entity.Sexo;

@ManagedBean(name = "autorizadoBean")
@SessionScoped
public class AutorizadoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private GenericDAO<Autorizado, Integer> autorizadoDAO = (GenericDAO<Autorizado, Integer>) BeanFactory
			.getBean("autorizadoDAO");
	private GenericDAO<Sexo, Integer> sexoDAO = (GenericDAO<Sexo, Integer>) BeanFactory
			.getBean("sexoDAO");
	private Autorizado autorizado;
	private EstadoCivil estadoCivil;
	private Sexo sexo;
	@ManagedProperty(value="#{matriculaSessionBean}")
	private MatriculaSessionBean matriculaSessionBean;
	private boolean incluiu;
	
	public AutorizadoBean() {
		atualizarTela();
	}

	public void gravar() {
		autorizado.setFkSexo(sexo);
		if(matriculaSessionBean.isAlunoNovo())
		{
			List<Autorizado> autorizados = matriculaSessionBean.getListaAutorizados();
			if(autorizado.getIdautorizado() !=null)
			{
				autorizados.remove(autorizados.indexOf(autorizado));
			}
			autorizados.add(autorizado);
		}
		else
		{
			getAutorizadoDAO().save(autorizado);
			
		}
		setIncluiu(true);
		atualizarTela();
	}

	public void limparIncluiu()
	{
		setIncluiu(false);
	}
	private void atualizarTela() {
		setAutorizado(new Autorizado());
		setSexo(new Sexo());
		setEstadoCivil(new EstadoCivil());
		if(matriculaSessionBean!=null && !matriculaSessionBean.isAlunoNovo())
		{
			matriculaSessionBean.setListaAutorizados(getAutorizadoDAO().listAll());
		}
	}

	public void excluir() {
		if(! getMatriculaSessionBean().isAlunoNovo())
		{
			getAutorizadoDAO().delete(autorizado);
		}
		else
		{
			matriculaSessionBean.getListaAutorizados().remove( autorizado);
		}
		atualizarTela();
	}

	public GenericDAO<Autorizado, Integer> getAutorizadoDAO() {
		return autorizadoDAO;
	}

	public void setAutorizadoDAO(GenericDAO<Autorizado, Integer> autorizadoDAO) {
		this.autorizadoDAO = autorizadoDAO;
	}

	public Autorizado getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Autorizado autorizado) {
		this.autorizado = autorizado;
	}

	public GenericDAO<Sexo, Integer> getSexoDAO() {
		return sexoDAO;
	}

	public void setSexoDAO(GenericDAO<Sexo, Integer> sexoDAO) {
		this.sexoDAO = sexoDAO;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public MatriculaSessionBean getMatriculaSessionBean() {
		return matriculaSessionBean;
	}

	public void setMatriculaSessionBean(MatriculaSessionBean matriculaSessionBean) {
		this.matriculaSessionBean = matriculaSessionBean;
	}

	public boolean isIncluiu() {
		return incluiu;
	}

	public void setIncluiu(boolean incluiu) {
		this.incluiu = incluiu;
	}

	public void gravarListaSessao(Aluno aluno) {
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno);
		for(Autorizado autorizado : matriculaSessionBean.getListaAutorizados())
		{
			autorizado.setAlunoList(alunos);
			getAutorizadoDAO().save(autorizado);
		}

	}
	
}
