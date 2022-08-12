package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Cidade;
import br.com.pirralhos.model.entity.Estado;
import br.com.pirralhos.model.entity.EstadoCivil;
import br.com.pirralhos.model.entity.Perfil;
import br.com.pirralhos.model.entity.Professor;
import br.com.pirralhos.model.entity.Sexo;
import br.com.pirralhos.model.entity.TelefoneCreche;
import br.com.pirralhos.model.entity.Usuario;
import br.com.pirralhos.view.constants.ViewConstants;
import br.com.pirralhos.view.utils.SelectItemUtils;

@ViewScoped
@ManagedBean(name = "professorBean")
public class ProfessorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SELECIONE = "SELECIONE";
	private GenericDAO<Professor, Integer> professorDAO = (GenericDAO<Professor, Integer>) BeanFactory
			.getBean("professorDAO");
	private GenericDAO<Sexo, Integer> sexoDAO = (GenericDAO<Sexo, Integer>) BeanFactory
			.getBean("sexoDAO");
	private GenericDAO<Cidade, Integer> cidadeDAO = (GenericDAO<Cidade, Integer>) BeanFactory
			.getBean("cidadeDAO");
	private GenericDAO<Estado, Integer> estadoDAO = (GenericDAO<Estado, Integer>) BeanFactory
			.getBean("estadoDAO");
	private GenericDAO<EstadoCivil, Integer> estadoCivilDAO = (GenericDAO<EstadoCivil, Integer>) BeanFactory
			.getBean("estadoCivilDAO");
	private GenericDAO<TelefoneCreche, Integer> telefoneCrecheDAO = (GenericDAO<TelefoneCreche, Integer>) BeanFactory
			.getBean("telefoneCrecheDAO");
	private GenericDAO<Usuario, Integer> usuarioDAO = (GenericDAO<Usuario, Integer>) BeanFactory
			.getBean("usuarioDAO");

	private Professor professor;
	private List<Cidade> listaCidades;
	private List<Estado> listaEstado;
	private List<Professor> listaProfessores;
	private List<TelefoneCreche> listaTelefonesCreche;

	private List<SelectItem> selectItensCidades;
	private List<SelectItem> selectItensEstados;
	private List<SelectItem> selectItensEstadosCivis;
	private List<SelectItem> selectItensTelefonesCreche;

	private Integer idSexo;
	private Integer idCidade;
	private Integer idEstado;
	private Integer idEstadoCivil;
	private Integer idTelefoneCreche;
	private String login;
	
	private boolean alterou;

	public ProfessorBean() {
		atualizarTela();
	}

	public String gravar() {
		professor.setFkEstado(new Estado(getIdEstado()));
		Cidade cidade = this.getCidadeDAO().findById(this.getIdCidade());
		professor.setFkCidade(cidade);
		professor.setFkEstadoCivl(new EstadoCivil(getIdEstadoCivil()));
		professor.setFkSexo(new Sexo(getIdSexo()));
		professor
				.setFkTelefoneCreche(new TelefoneCreche(getIdTelefoneCreche()));
		
		Usuario usuario = professor.getFkUsuario();
		if (usuario == null) {
			usuario = new Usuario();
			Perfil perfil = new Perfil(ViewConstants.PERFIL_PROFESSOR);
			usuario.setFkPerfil(perfil);
			usuario.setIdusuario(login);
		}
		if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
			usuario.setSenha(ViewConstants.SENHA_PADRAO);
			usuario.setTrocarSenha(true);
		} else {
			usuario.setTrocarSenha(false);
		}
		getProfessor().setFkUsuario(getUsuarioDAO().save(usuario));
		getProfessorDAO().save(getProfessor());
		atualizarTela();
		this.professor = new Professor();
		return "/consulta_professor";
	}

	public void excluir() {
		getProfessorDAO().delete(getProfessor());
		getUsuarioDAO().delete(getProfessor().getFkUsuario());
		atualizarTela();
	}

	public void popularCidade() {
		if (idEstado != null && idEstado != 0) {
			setListaCidades(getCidadeDAO().findBySQL(
					"from Cidade where idestado=" + idEstado));
			setSelectItensCidades(SelectItemUtils.getSelectItems("idcidade",
					"descCidade", getListaCidades()));
		} else {
			setListaCidades(null);
			setSelectItensCidades(null);
			setIdCidade(null);
		}
	}

	private void atualizarTela() {
		limpar();
		setListaProfessores(getProfessorDAO().listAll());
		setListaEstado(getEstadoDAO().listAll());
		setSelectItensEstados(SelectItemUtils.getSelectItems("idestado",
				"descEstado", getListaEstado()));

		setSelectItensEstadosCivis(SelectItemUtils.getSelectItems(
				"idestadoCivil", "descEstadoCivil", getListaEstadosCivis()));

		setListaTelefonesCreche(getTelefoneCrecheDAO().listAll());

		carregarTelefonesCreche();
	}
	
	public void carregarTelefonesCreche()
	{
		// <[#46]>
		/*
		 * [#46] Combo Telefone Creche- Tela Professor Description: Apresentar
		 * no combo telefone creche apenas os telefones que não estão vinculados
		 * com professores
		 */
		List<TelefoneCreche> telefoneC = getTelefoneCrecheDAO().findBySQL("from TelefoneCreche telefoneCreche where telefoneCreche.professorList.size <1 ");


		setSelectItensTelefonesCreche(SelectItemUtils.getSelectItems(
				"idtelefoneCreche", "numero", telefoneC));
		// </[#46]>

	}

	public void limpar() {
		setProfessor(new Professor());
	}

	public GenericDAO<Professor, Integer> getProfessorDAO() {
		return professorDAO;
	}

	public void setProfessorDAO(GenericDAO<Professor, Integer> professorDAO) {
		this.professorDAO = professorDAO;
	}

	public GenericDAO<Sexo, Integer> getSexoDAO() {
		return sexoDAO;
	}

	public void setSexoDAO(GenericDAO<Sexo, Integer> sexoDAO) {
		this.sexoDAO = sexoDAO;
	}

	public GenericDAO<Cidade, Integer> getCidadeDAO() {
		return cidadeDAO;
	}

	public void setCidadeDAO(GenericDAO<Cidade, Integer> cidadeDAO) {
		this.cidadeDAO = cidadeDAO;
	}

	public GenericDAO<Estado, Integer> getEstadoDAO() {
		return estadoDAO;
	}

	public void setEstadoDAO(GenericDAO<Estado, Integer> estadoDAO) {
		this.estadoDAO = estadoDAO;
	}

	public GenericDAO<EstadoCivil, Integer> getEstadoCivilDAO() {
		return estadoCivilDAO;
	}

	public void setEstadoCivilDAO(
			GenericDAO<EstadoCivil, Integer> estadoCivilDAO) {
		this.estadoCivilDAO = estadoCivilDAO;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public List<EstadoCivil> getListaEstadosCivis() {
		return this.getEstadoCivilDAO().listAll();
	}

	public List<SelectItem> getSelectItensCidades() {
		return selectItensCidades;
	}

	public void setSelectItensCidades(List<SelectItem> selectItensCidades) {
		this.selectItensCidades = selectItensCidades;
	}

	public List<SelectItem> getSelectItensEstados() {
		return selectItensEstados;
	}

	public void setSelectItensEstados(List<SelectItem> selectItensEstados) {
		this.selectItensEstados = selectItensEstados;
	}

	public List<SelectItem> getSelectItensEstadosCivis() {
		return selectItensEstadosCivis;
	}

	public void setSelectItensEstadosCivis(
			List<SelectItem> selectItensEstadosCivis) {
		this.selectItensEstadosCivis = selectItensEstadosCivis;
	}

	public Integer getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(Integer idSexo) {
		this.idSexo = idSexo;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(Integer idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public List<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}

	public GenericDAO<TelefoneCreche, Integer> getTelefoneCrecheDAO() {
		return telefoneCrecheDAO;
	}

	public void setTelefoneCrecheDAO(
			GenericDAO<TelefoneCreche, Integer> telefoneCrecheDAO) {
		this.telefoneCrecheDAO = telefoneCrecheDAO;
	}

	public List<TelefoneCreche> getListaTelefonesCreche() {
		return listaTelefonesCreche;
	}

	public void setListaTelefonesCreche(
			List<TelefoneCreche> listaTelefonesCreche) {
		this.listaTelefonesCreche = listaTelefonesCreche;
	}

	public List<SelectItem> getSelectItensTelefonesCreche() {

		return selectItensTelefonesCreche;
	}

	public void setSelectItensTelefonesCreche(
			List<SelectItem> selectItensTelefonesCreche) {
		this.selectItensTelefonesCreche = selectItensTelefonesCreche;
	}

	public Integer getIdTelefoneCreche() {
		return idTelefoneCreche;
	}

	public void setIdTelefoneCreche(Integer idTelefoneCreche) {
		this.idTelefoneCreche = idTelefoneCreche;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public GenericDAO<Usuario, Integer> getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(GenericDAO<Usuario, Integer> usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void atualiza() {
		EstadoCivil estadoCivil = this.getEstadoCivilDAO().findById(
				this.idEstadoCivil);
		this.professor.setFkEstadoCivl(estadoCivil);
		TelefoneCreche telefoneCreche = this.getTelefoneCrecheDAO().findById(
				this.idTelefoneCreche);
		this.professor.setFkTelefoneCreche(telefoneCreche);
		Sexo sexo = this.getSexoDAO().findById(this.idSexo);
		this.professor.setFkSexo(sexo);
		Estado estado = this.getEstadoDAO().findById(this.idEstado);
		this.professor.setFkEstado(estado);
		Cidade cidade = this.getCidadeDAO().findById(this.idCidade);
		this.professor.setFkCidade(cidade);
		this.getProfessorDAO().save(this.professor);
		setAlterou(true);
		atualizarTela();
	}

	public void atualizarDadosProfessor() {
		this.setIdEstadoCivil(this.professor.getFkEstadoCivl()
				.getIdestadoCivil());
		this.setIdTelefoneCreche(this.professor.getFkTelefoneCreche()
				.getIdtelefoneCreche());
		this.setIdSexo(this.professor.getFkSexo().getIdsexo());
		this.setIdEstado(this.professor.getFkEstado().getIdestado());
		this.popularCidade();
		this.setIdCidade(this.professor.getFkCidade().getIdcidade());
		this.setLogin(this.professor.getFkUsuario().getIdusuario());
		carregarTelefonesCreche();
		getSelectItensTelefonesCreche().add(new SelectItem(professor.getFkTelefoneCreche().getIdtelefoneCreche(),professor.getFkTelefoneCreche().getNumero()));
		setAlterou(false);
	}

	public boolean isAlterou() {
		return alterou;
	}

	public void setAlterou(boolean alterou) {
		this.alterou = alterou;
	}

	public String getUsuarioValido() {
		if(login == null || login.equals(""))
			return null;
		int indice = getUsuarioDAO().listAll().indexOf(new Usuario(login));
		if(indice > 0)
		{
			return "Usuário existente";
		}
		return null;
	}

	
}
