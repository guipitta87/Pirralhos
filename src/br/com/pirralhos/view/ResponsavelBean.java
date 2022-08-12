package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;
import br.com.pirralhos.model.entity.Cidade;
import br.com.pirralhos.model.entity.Estado;
import br.com.pirralhos.model.entity.Perfil;
import br.com.pirralhos.model.entity.Responsavel;
import br.com.pirralhos.model.entity.Sexo;
import br.com.pirralhos.model.entity.Usuario;
import br.com.pirralhos.view.constants.ViewConstants;

@ManagedBean(name = "responsavelBean")
@SessionScoped
public class ResponsavelBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private GenericDAO<Responsavel, Integer> responsavelDAO = (GenericDAO<Responsavel, Integer>) BeanFactory
			.getBean("responsavelDAO");
	private GenericDAO<Sexo, Integer> sexoDAO = (GenericDAO<Sexo, Integer>) BeanFactory
			.getBean("sexoDAO");
	private GenericDAO<Cidade, Integer> cidadeDAO = (GenericDAO<Cidade, Integer>) BeanFactory
			.getBean("cidadeDAO");
	private GenericDAO<Estado, Integer> estadoDAO = (GenericDAO<Estado, Integer>) BeanFactory
			.getBean("estadoDAO");
	private GenericDAO<Usuario, Integer> usuarioDAO = (GenericDAO<Usuario, Integer>) BeanFactory
			.getBean("usuarioDAO");
	private Responsavel responsavel;
	private Cidade cidade;
	private Estado estado;
	private Sexo sexo;
	private Usuario usuario;
	private Integer idcidade;
	private Integer idestado;
	private Integer idsexo;
	private Integer idusuario;
	@ManagedProperty(value = "#{matriculaSessionBean}")
	private MatriculaSessionBean matriculaSessionBean;
	private boolean incluiu;
	private SelectItem maeItem;
	private SelectItem paiItem;
	private String veririficaResp;

	public ResponsavelBean() {
		atualizarTela();
	}

	public void gravar() {
		// No trecho abaixo eh criada a relaï¿½ï¿½o entre responsavel->sexo e
		// responsavel->cidade
		Cidade fkCidade = this.getCidadeDAO().findById(this.idcidade);
		Sexo fkSexo = this.getSexoDAO().findById(this.idsexo);
		Estado fkEstado = this.getEstadoDAO().findById(this.idestado);

		this.responsavel.setFkCidade(fkCidade);
		this.responsavel.setFkSexo(fkSexo);
		this.responsavel.setFkEstado(fkEstado);
		Perfil perfil = new Perfil(ViewConstants.PERFIL_RESPONSAVEL);
		usuario.setFkPerfil(perfil);
		if (usuario.getSenha() == null || usuario.getSenha().equals("")) {
			usuario.setSenha(ViewConstants.SENHA_PADRAO);
			usuario.setTrocarSenha(true);
		} else {
			usuario.setTrocarSenha(false);
		}

		this.responsavel.setFkUsuario(getUsuarioDAO().save(usuario));

		List<Responsavel> responsaveis = matriculaSessionBean
				.getListaResponsaveis();
		if (matriculaSessionBean.isAlunoNovo()) {

			if (responsavel.getIdresponsavel() != null) {
				responsaveis.remove(responsaveis.indexOf(responsavel));
			}
			responsaveis.add(responsavel);
		} else {
			getResponsavelDAO().save(responsavel);
		}
		setIncluiu(true);
		atualizarTela();
	}

	public void limparIncluiu() {
		setIncluiu(false);
		limpar();
	}

	public void limpar() {
		setResponsavel(new Responsavel());
		setIdestado(0);
		setIdsexo(0);
		setIdcidade(0);
		setUsuario(new Usuario());
	}

	private void atualizarTela() {
		limpar();
		if (matriculaSessionBean != null && !matriculaSessionBean.isAlunoNovo()) {
			matriculaSessionBean.setListaResponsaveis(getResponsavelDAO()
					.listAll());
		}
	}

	public void excluir() {
		if (!getMatriculaSessionBean().isAlunoNovo()) {
			getResponsavelDAO().delete(responsavel);
			getUsuarioDAO().delete(responsavel.getFkUsuario());
		} else {
			matriculaSessionBean.getListaResponsaveis().remove(responsavel);
		}
		atualizarTela();
	}

	public GenericDAO<Responsavel, Integer> getResponsavelDAO() {
		return responsavelDAO;
	}

	public void setResponsavelDAO(
			GenericDAO<Responsavel, Integer> responsavelDAO) {
		this.responsavelDAO = responsavelDAO;
	}

	public GenericDAO<Sexo, Integer> getSexoDAO() {
		return sexoDAO;
	}

	public void setSexoDAO(GenericDAO<Sexo, Integer> sexoDAO) {
		this.sexoDAO = sexoDAO;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
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

	public List<Cidade> getListaCidades() {
		return this.getCidadeDAO().listAll();
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Integer getIdcidade() {
		return idcidade;
	}

	public void setIdcidade(Integer idcidade) {
		this.idcidade = idcidade;
	}

	public Integer getIdsexo() {
		return idsexo;
	}

	public void setIdsexo(Integer idsexo) {
		this.idsexo = idsexo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public GenericDAO<Usuario, Integer> getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(GenericDAO<Usuario, Integer> usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public Integer getIdestado() {
		return idestado;
	}

	public void setIdestado(Integer idestado) {
		this.idestado = idestado;
	}

	public MatriculaSessionBean getMatriculaSessionBean() {
		return matriculaSessionBean;
	}

	public void setMatriculaSessionBean(
			MatriculaSessionBean matriculaSessionBean) {
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
		for (Responsavel responsavel : matriculaSessionBean
				.getListaResponsaveis()) {
			responsavel.setAlunoList(alunos);
			getResponsavelDAO().save(responsavel);
		}

	}

	public SelectItem getMaeItem() {
		return maeItem;
	}

	public void setMaeItem(SelectItem maeItem) {
		this.maeItem = maeItem;
	}

	public SelectItem getPaiItem() {
		return paiItem;
	}

	public void setPaiItem(SelectItem paiItem) {
		this.paiItem = paiItem;
	}

	public String getVeririficaResp() {
		return veririficaResp = "O";
	}

	public void setVeririficaResp(String veririficaResp) {
		this.veririficaResp = veririficaResp;
	}
	public String getUsuarioValido() {
		if(usuario.getIdusuario() == null || usuario.getIdusuario().equals(""))
			return null;
		int indice = getUsuarioDAO().listAll().indexOf(usuario);
		if(indice > 0)
		{
			return "Usuário existente";
		}
		return null;
	}

}
