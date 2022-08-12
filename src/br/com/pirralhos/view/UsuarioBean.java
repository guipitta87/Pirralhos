package br.com.pirralhos.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Perfil;
import br.com.pirralhos.model.entity.Usuario;
import br.com.pirralhos.view.constants.ViewConstants;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean usuarioInvalido;
	private GenericDAO<Perfil, Integer> perfilDAO = (GenericDAO<Perfil, Integer>) BeanFactory.getBean("perfilDAO");
	private GenericDAO<Usuario, Integer> usuarioDAO = (GenericDAO<Usuario, Integer>) BeanFactory.getBean("usuarioDAO");
	private Usuario usuario;
	private String idusuario;
	private String senha;
	private Integer codPerfil;
	private String idlogin;
	private Perfil perfil = new Perfil();
	@ManagedProperty(value="#{dashboardProfessorBean}")
	private DashboardProfessorBean dashboardProfessorBean;
	@ManagedProperty(value="#{dashboardResponsavelBean}")
	private DashboardResponsavelBean dashboardResponsavelBean;
	private boolean alteracao;
	public UsuarioBean() {
		limpar();
	}

	public String alterarSenha()
	{
		String iduser = usuario.getIdusuario();
		alteracao= true;
		setSenha(usuario.getSenha());
		perfil = usuario.getFkPerfil();
		gravar();
		setIdusuario(iduser);
		return login();
	}
	
	public void atribuiAlterar()
	{
		alteracao = true;
	}
	public void gravar() {
		if(!alteracao)
		{
			int indice = getUsuarioDAO().listAll().indexOf(usuario);
			if(indice > 0)
			{
				FacesMessage message = new FacesMessage(
						"Usuário existente");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage("painel", message);
				return;
			}

		}
		if(usuario.getSenha() == null || usuario.getSenha().equals(""))
		{
			usuario.setSenha(ViewConstants.SENHA_PADRAO);
			usuario.setTrocarSenha(true);
		}
		else
		{
			usuario.setTrocarSenha(false);
		}
		this.usuario.setFkPerfil(perfil);
		this.getUsuarioDAO().save(this.usuario);
		limpar();
	}

	public String limpar(){
		alteracao =false;
		setUsuario(new Usuario());
		setPerfil(new Perfil());
		return "cadastro_usuario";
	}

	public void excluir() {
		getUsuarioDAO().delete(usuario);
		limpar();
	}

	public List<Usuario> getListaUsuarios() {
		return getUsuarioDAO().listAll();
	}

	public String login() {
		List<Usuario> usuarios = getUsuarioDAO().findBySQL(
				"from Usuario where idusuario='" + getIdusuario() + "'"
						+ "and senha='" +getSenha() + "'");
		FacesContext fc = FacesContext.getCurrentInstance();
		setIdusuario("");
		setSenha("");
		if (usuarios == null || usuarios.size() == 0) {
			setUsuarioInvalido(true);
			
		}
		if (usuarios.size() == 1) {
			Usuario usuario = usuarios.get(0);
			java.util.Map<String, Object> sessionMap = fc.getExternalContext()
					.getSessionMap();
			if(usuario.getTrocarSenha())
			{
				usuario.setSenha("");
				this.usuario = usuario;
				return "trocarSenha";
			}
			setUsuarioInvalido(false);
			idlogin = usuario.getIdusuario();
			sessionMap.put("ID_USUARIO", idlogin );
			codPerfil = usuario.getFkPerfil().getIdperfil();
			sessionMap.put("PERFIL", codPerfil );
			switch (codPerfil) {
			case ViewConstants.PERFIL_ADMINISTRADOR:
				return "home_administrador";
			case ViewConstants.PERFIL_PROFESSOR:
				return  dashboardProfessorBean.atualizarTela();
			case ViewConstants.PERFIL_RESPONSAVEL:
				return 	dashboardResponsavelBean.atualizarTela();
			}

		}
		return "home";
	}

	public void logout() throws IOException {
		java.util.Map<String, Object> sessionMap = FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.remove("ID_USUARIO");
		sessionMap.remove("PERFIL");
		idlogin="";
		codPerfil=0;
		FacesContext
		.getCurrentInstance().getExternalContext().redirect("home.xhtml");
	}

	public GenericDAO<Usuario, Integer> getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(GenericDAO<Usuario, Integer> usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public GenericDAO<Perfil, Integer> getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(GenericDAO<Perfil, Integer> perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(Integer codPerfil) {
		this.codPerfil = codPerfil;
	}

	public String getIdlogin() {
		return idlogin;
	}
	public void setIdlogin(String idlogin) {
		this.idlogin = idlogin;
	}

	public boolean isUsuarioInvalido() {
		return usuarioInvalido;
	}

	public void setUsuarioInvalido(boolean usuarioInvalido) {
		this.usuarioInvalido = usuarioInvalido;
	}

	public DashboardProfessorBean getDashboardProfessorBean() {
		return dashboardProfessorBean;
	}

	public void setDashboardProfessorBean(
			DashboardProfessorBean dashboardProfessorBean) {
		this.dashboardProfessorBean = dashboardProfessorBean;
	}

	public DashboardResponsavelBean getDashboardResponsavelBean() {
		return dashboardResponsavelBean;
	}

	public void setDashboardResponsavelBean(
			DashboardResponsavelBean dashboardResponsavelBean) {
		this.dashboardResponsavelBean = dashboardResponsavelBean;
	}
	
	public Integer getPerfilUsuario()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		java.util.Map<String, Object> sessionMap = fc.getExternalContext()
				.getSessionMap();
		return Integer.valueOf(sessionMap.get("PERFIL").toString());
	}
	
}
